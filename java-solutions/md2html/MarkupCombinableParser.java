package md2html;

import markup.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class MarkupCombinableParser {
    private final static List<TagDescription> COMBINABLE_TAGS = List.of(
            new TagDescription("```", null),
            new TagDescription("__", Strong::new),
            new TagDescription("**", Strong::new),
            new TagDescription("--", Strikeout::new),
            new TagDescription("*", Emphasis::new),
            new TagDescription("_", Emphasis::new),
            new TagDescription("`", Code::new)
    );

    private MarkupCombinableParser() {
    }

    public static MarkupCombinable parseMD(final PositionedString data) {
        if (data.isExhausted()) {
            return null;
        }

        final String detectedTag = detectOpenTagType(data, "```");
        if (detectedTag != null) {
            data.incPos(detectedTag.length());
            final int startPos = data.getPos();
            final StringBuilder unformattedText = new StringBuilder();
            while (!data.isExhausted()) {
                if (data.isTag(detectedTag)) {
                    data.incPos(detectedTag.length());
                    return new Unformatted(new Text(unformattedText.toString()));
                }
                unformattedText.append(data.getChar());
                data.incPos();
            }
            data.setPos(startPos);
        }

        for (final var i : COMBINABLE_TAGS) {
            if (i.constructor != null) {
                final MarkupCombinable parsed = tryParse(data, i);
                if (parsed != null) {
                    return parsed;
                }
            }
        }

        final StringBuilder textBuilder = new StringBuilder();

        do {
            if (data.getChar() != '\\') {
                textBuilder.append(data.getChar());
            }
            data.incPos();
        } while (!data.isExhausted() && !isTagSymbol(data) &&
                !(data.startsWith("\r\n\r\n", "\n\n")));

        if (data.isExhausted()) {
            while (textBuilder.length() > 0 &&
                    "\r\n".contains(textBuilder.subSequence(textBuilder.length() - 1, textBuilder.length()))) {
                textBuilder.deleteCharAt(textBuilder.length() - 1);
            }
        }
        return new Text(textBuilder.toString());
    }

    private static MarkupCombinable tryParse(final PositionedString data, final TagDescription tag) {
        final List<MarkupCombinable> result = new ArrayList<>();

        final String detectedTag = detectOpenTagType(data, tag.tag);
        if (detectedTag != null) {
            data.incPos(detectedTag.length());
            while (!data.isExhausted() && !isCloseTag(data, detectedTag)) {
                result.add(parseMD(data));
            }
            data.incPos(detectedTag.length());
            return tag.constructor.apply(result);
        }
        return null;
    }

    private static boolean isTagSymbol(final PositionedString data) {
        for (final TagDescription i : COMBINABLE_TAGS) {
            if (data.isTag(i.tag)) {
                return true;
            }
        }
        return false;
    }

    private static String detectOpenTagType(final PositionedString data, final String... tag) {
        for (final String i : tag) {
            if (data.isValidOpenTag(i)) {
                return i;
            }
        }
        return null;
    }

    private static boolean isCloseTag(final PositionedString data, final String... tag) {
        for (final String i : tag) {
            if (data.isValidCloseTag(i)) {
                for (final TagDescription j : COMBINABLE_TAGS) {
                    if (j.tag.length() > i.length() && data.isValidCloseTag(j.tag)) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    private record TagDescription(String tag, Function<List<MarkupCombinable>, MarkupCombinable> constructor) {
    }
}
