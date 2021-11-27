package md2html;

import markup.*;

import java.util.ArrayList;
import java.util.List;

public class MarkupStructureParser {
    private MarkupStructureParser() {
    }

    private static MarkupStructure parseSingleStructure(final PositionedString data) {
        if (data.isExhausted()) {
            return null;
        }

        final List<MarkupCombinable> result = new ArrayList<>();

        final int headlineLevel = validateHeadlineBorder(data);
        if (headlineLevel > 0 || validateParagraph(data)) {
            while (!data.isExhausted() && !data.startsWith("\n\n", "\r\n\r\n")) {
                final var parsed = MarkupCombinableParser.parseMD(data);
                if (parsed == null) {
                    throw new AssertionError("Couldn't parse a markup object");
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

    private static int validateHeadlineBorder(final PositionedString data) {
        int startPos = data.getPos();
        int level = 0;
        while (data.isTag("#")) {
            level++;
            data.incPos();
        }
        if (level > 0 && !data.isExhausted() && data.getChar() == ' ') {
            data.incPos();
            return level;
        }
        data.setPos(startPos);
        return 0;
    }

    private static boolean validateParagraph(final PositionedString data) {
        return !data.isExhausted() && "\r\n".indexOf(data.getChar()) == -1;
    }

    public static Document parseMarkdown(final String data) {
        PositionedString positionedData = new PositionedString(data);
        final List<MarkupStructure> result = new ArrayList<>();

        while (!positionedData.isExhausted()) {
            final MarkupStructure struct = parseSingleStructure(positionedData);

            if (struct == null) {
                positionedData.incPos();
            } else {
                result.add(struct);
            }
        }

        return new Document(result);
    }
}
