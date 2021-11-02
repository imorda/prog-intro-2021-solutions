package markup;

import java.util.List;

public class Strong extends MarkupSymmetricClass {
    private final static String MD_HIGHLIGHT_SYMBOL = "__";
    private final static String BB_HIGHLIGHT_SYMBOL = "b";

    public Strong(List<MarkupSerializable> contentData) {
        super(contentData, MD_HIGHLIGHT_SYMBOL, BB_HIGHLIGHT_SYMBOL);
    }

    public Strong(MarkupSerializable contentData) {
        super(contentData, MD_HIGHLIGHT_SYMBOL, BB_HIGHLIGHT_SYMBOL);
    }
}
