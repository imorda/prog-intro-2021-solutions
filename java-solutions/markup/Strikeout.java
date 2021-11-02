package markup;

import java.util.List;

public class Strikeout extends MarkupSymmetricClass {
    private final static String MD_HIGHLIGHT_SYMBOL = "~";
    private final static String BB_HIGHLIGHT_SYMBOL = "s";

    public Strikeout(List<MarkupSerializable> contentData) {
        super(contentData, MD_HIGHLIGHT_SYMBOL, BB_HIGHLIGHT_SYMBOL);
    }

    public Strikeout(MarkupSerializable contentData) {
        super(contentData, MD_HIGHLIGHT_SYMBOL, BB_HIGHLIGHT_SYMBOL);
    }
}
