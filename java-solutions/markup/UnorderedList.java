package markup;

public class UnorderedList extends List {
    public UnorderedList(java.util.List<MarkupSerializable> contentData) throws NullPointerException {
        super(contentData, "");
    }

    public UnorderedList(MarkupSerializable contentData) throws NullPointerException {
        this(java.util.List.of(contentData));
    }
}
