package markup;

public class List extends MarkupClass {
    public List(java.util.List<MarkupSerializable> contentData, final String bbTagMod) throws NullPointerException {
        super(contentData, "", "", "[list" + bbTagMod + "]", "[/list]");
    }

    public List(MarkupSerializable contentData, final String tagMod) throws NullPointerException {
        this(java.util.List.of(contentData), tagMod);
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        throw new UnsupportedOperationException("Markdown is unsupported for ListItem");
    }
}
