package markup;

import java.util.List;

public class Strikeout extends MarkupClass {
    private final static String HIGHLIGHT_SYMBOL = "~";

    public Strikeout(List<MarkdownSerializable> contentData) {
        super(contentData, HIGHLIGHT_SYMBOL);
    }

    public Strikeout(MarkdownSerializable contentData) {
        super(contentData, HIGHLIGHT_SYMBOL);
    }
}
