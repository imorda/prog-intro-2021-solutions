package md2html;

import markup.*;

import java.util.ArrayList;
import java.util.List;

public class MarkupStructureParser {
    private MarkupStructureParser() {
    }

    public static MarkupStructure parseMD(final String data, final MutableInt range) {
        if (range.get() >= data.length()) {
            return null;
        }

        final List<MarkupCombinable> result = new ArrayList<>();

        final int headlineLevel = validateHeadlineBorder(data, range);
        if (headlineLevel > 0 || validateParagraph(data, range)) {
            while (range.get() < data.length() && !data.startsWith("\n\n", range.get()) &&
                    !data.startsWith("\r\n\r\n", range.get())) {
                final var parsed = MarkupCombinableParser.parseMD(data, range);
                if (parsed == null) {
                    throw new AssertionError();
                }
                result.add(parsed);
            }

            if (headlineLevel > 0) {
                return new Headline(result, headlineLevel);
            }
            return new MultilineParagraph(result);
        }

        return null;
    }

    private static int validateHeadlineBorder(final String data, final MutableInt start) {
        int pos = start.get();
        if (pos < data.length() && !(pos > 0 && data.charAt(pos - 1) == '\\')) {
            int level = 0;
            while (pos < data.length() && data.charAt(pos) == '#') {
                level++;
                pos++;
            }
            if (level > 0 && pos < data.length() && data.charAt(pos) == ' ') {
                start.set(pos + 1);
                return level;
            }
        }
        return 0;
    }

    private static boolean validateParagraph(final String data, final MutableInt range) {
        return range.get() < data.length() && data.charAt(range.get()) != '\n' && data.charAt(range.get()) != '\r';
    }

    public static Document parseMarkdown(final String data) {
        final MutableInt pos = new MutableInt(0);

        final List<MarkupStructure> result = new ArrayList<>();

        while (pos.get() < data.length()) {
            final MarkupStructure struct = parseMD(data, pos);

            if (struct == null) {
                pos.set(pos.get() + 1);
            } else {
                result.add(struct);
            }
        }

        return new Document(result);
    }
}
