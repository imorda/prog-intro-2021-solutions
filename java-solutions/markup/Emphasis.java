package markup;

import java.util.List;

public class Emphasis extends MarkupSymmetricClass {
    private final static String MD_HIGHLIGHT_SYMBOL = "*";
    private final static String BB_HIGHLIGHT_SYMBOL = "i";

    public Emphasis(List<MarkupSerializable> contentData) {
        super(contentData, MD_HIGHLIGHT_SYMBOL, BB_HIGHLIGHT_SYMBOL);
    }

    public Emphasis(MarkupSerializable contentData) {
        super(contentData, MD_HIGHLIGHT_SYMBOL, BB_HIGHLIGHT_SYMBOL);
    }
}
