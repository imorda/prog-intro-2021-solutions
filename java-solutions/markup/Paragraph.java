package markup;

import md2html.MarkupCombinableParser;

import java.util.List;

public class Paragraph extends AbstractMarkupTaggedGroup implements MarkupStructure {
    private static final Tag EMPTY_TAG = new Tag("", "", "", "", "");
    private static final Tag TAG = new Tag("",
            "", "",
            "<p>", "</p>" + System.lineSeparator());

    public Paragraph(List<MarkupCombinable> content, Tag customTag) {
        super(content, customTag);
    }

    public Paragraph(MarkupCombinable content, Tag customTag) {
        super(content, customTag);
    }

    public Paragraph(List<MarkupCombinable> content) {
        this(content, EMPTY_TAG);
    }

    public Paragraph(MarkupCombinable content) {
        this(content, EMPTY_TAG);
    }

    public static Paragraph parseMD(String data, MutableRange range) {
        findRightBorderMDImpl(data, range, 0, "\n\n", "\r\n\r\n");
        if (range.to - range.from <= 2) {
            return null;
        }

        MutableRange newRange = range.copy();
        while (data.charAt(newRange.to - 1) == '\n' || data.charAt(newRange.to - 1) == '\r') {
            newRange.to--;
        }

        return new Paragraph(MarkupCombinableParser.getInstance().parseMD(data, newRange), TAG);
    }
}
