package markup;

public class Tag {
    private final String md;
    private final String mdAlt;
    private final String bbOpen;
    private final String bbClose;
    private final String htmlOpen;
    private final String htmlClose;

    public Tag(String md, String mdAlt, String bbOpen, String bbClose, String htmlOpen, String htmlClose) {
        this.md = md;
        this.mdAlt = mdAlt;
        this.bbOpen = bbOpen;
        this.bbClose = bbClose;
        this.htmlOpen = htmlOpen;
        this.htmlClose = htmlClose;
    }

    public Tag(String md, String bbOpen, String bbClose, String htmlOpen, String htmlClose) {
        this(md, md, bbOpen, bbClose, htmlOpen, htmlClose);
    }

    public Tag(String md, String bb, String html) {
        this(md, md,
                "[" + bb + "]", "[/" + bb + "]",
                "<" + html + ">", "</" + html + ">");
    }

    public String getMd() {
        return md;
    }

    public String getMdAlt() {
        return mdAlt;
    }

    public String getBbOpen() {
        return bbOpen;
    }

    public String getBbClose() {
        return bbClose;
    }

    public String getHtmlOpen() {
        return htmlOpen;
    }

    public String getHtmlClose() {
        return htmlClose;
    }
}
