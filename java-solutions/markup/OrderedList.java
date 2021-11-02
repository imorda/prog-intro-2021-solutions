package markup;

public class OrderedList extends List {
    public OrderedList(java.util.List<MarkupSerializable> contentData) throws NullPointerException {
        super(contentData, "=1");
    }

    public OrderedList(MarkupSerializable contentData) throws NullPointerException {
        this(java.util.List.of(contentData));
    }
}
