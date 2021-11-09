package markup;

public class Tag {
    private final String md;
    private final String bbOpen;
    private final String bbClose;

    public Tag(String md, String bbOpen, String bbClose) {
        this.md = md;
        this.bbOpen = bbOpen;
        this.bbClose = bbClose;
    }

    public Tag(String md, String bb) {
        this(md, "[" + bb + "]", "[/" + bb + "]");
    }

    public String getMd() {
        return md;
    }

    public String getBbOpen() {
        return bbOpen;
    }

    public String getBbClose() {
        return bbClose;
    }
}
