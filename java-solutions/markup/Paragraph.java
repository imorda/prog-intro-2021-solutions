package markup;

import java.util.List;

public class Paragraph extends MarkupClass {
    private final static String HIGHLIGHT_SYMBOL = "";

    public Paragraph(List<MarkdownSerializable> contentData) {
        super(contentData, HIGHLIGHT_SYMBOL);
    }

    public Paragraph(MarkdownSerializable contentData) {
        super(contentData, HIGHLIGHT_SYMBOL);
    }
}