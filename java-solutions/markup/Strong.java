package markup;

import java.util.List;

public class Strong extends MarkupClass {
    private final static String HIGHLIGHT_SYMBOL = "__";

    public Strong(List<MarkdownSerializable> contentData) {
        super(contentData, HIGHLIGHT_SYMBOL);
    }

    public Strong(MarkdownSerializable contentData) {
        super(contentData, HIGHLIGHT_SYMBOL);
    }
}
