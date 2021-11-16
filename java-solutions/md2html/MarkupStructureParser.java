package md2html;

import markup.Headline;
import markup.MarkupStructure;
import markup.MutableRange;
import markup.Paragraph;

public class MarkupStructureParser extends AbstractMarkupParser<MarkupStructure> {
    private static MarkupStructureParser singleton;

    private MarkupStructureParser() {
    }

    public static MarkupStructureParser getInstance() {
        if (singleton == null) {
            singleton = new MarkupStructureParser();
        }
        return singleton;
    }

    @Override
    protected MarkupStructure parseOneElementMD(String data, MutableRange range) {
        MarkupStructure result;

        while (range.from < data.length() && data.charAt(range.from) == '\n' || data.charAt(range.from) == '\r') {
            range.from++;
        }
        result = Headline.parseMD(data, range);
        if (result == null) {
            result = Paragraph.parseMD(data, range);
        }

        return result;
    }
}
