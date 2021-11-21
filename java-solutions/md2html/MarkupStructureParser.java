package md2html;

import markup.Headline;
import markup.MarkupCombinable;
import markup.MarkupStructure;
import markup.MultilineParagraph;

import java.util.ArrayList;
import java.util.List;

public class MarkupStructureParser {
    private MarkupStructureParser() {
    }

    public static MarkupStructure parseMD(String data, MutableInt range) {
        if (range.val >= data.length()) {
            return null;
        }

        List<MarkupCombinable> result = new ArrayList<>();

        int headlineLevel = validateHeadlineBorder(data, range);
        if (headlineLevel > 0 || validateParagraph(data, range)) {
            while (range.val < data.length() && !data.startsWith("\n\n", range.val) &&
                    !data.startsWith("\r\n\r\n", range.val)) {
                var parsed = MarkupCombinableParser.parseMD(data, range);
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

    private static int validateHeadlineBorder(String data, MutableInt range) {
        int pos = range.val;
        if (pos < data.length() && !(pos > 0 && data.charAt(pos - 1) == '\\')) {
            int level = 0;
            while (pos < data.length() && data.charAt(pos) == '#') {
                level++;
                pos++;
            }
            if (level > 0 && pos < data.length() && data.charAt(pos) == ' ') {
                range.val = pos + 1;
                return level;
            }
        }
        return 0;
    }

    private static boolean validateParagraph(String data, MutableInt range) {
        return range.val < data.length() && data.charAt(range.val) != '\n' && data.charAt(range.val) != '\r';
    }
}
