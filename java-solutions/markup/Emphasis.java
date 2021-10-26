package markup;

import java.util.List;

public class Emphasis extends MarkupClass {
    private final static String HIGHLIGHT_SYMBOL = "*";

    public Emphasis(List<MarkdownSerializable> contentData) {
        super(contentData, HIGHLIGHT_SYMBOL);
    }

    public Emphasis(MarkdownSerializable contentData) {
        super(contentData, HIGHLIGHT_SYMBOL);
    }
}
