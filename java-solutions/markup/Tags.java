package markup;

public enum Tags {
    EMPHASIS("*", "i"),
    STRONG("__", "b"),
    STRIKEOUT("~", "s"),
    ORDERED_LIST("", "[list=1]", "[/list]"),
    UNORDERED_LIST("", "list"),
    LIST_ITEM("", "[*]", "");

    public final String md;
    public final String bbOpen;
    public final String bbClose;

    Tags(String md, String bbOpen, String bbClose) {
        this.md = md;
        this.bbClose = bbClose;
        this.bbOpen = bbOpen;
    }

    Tags(String md, String bb) {
        this(md, "[" + bb + "]", "[/" + bb + "]");
    }
}
