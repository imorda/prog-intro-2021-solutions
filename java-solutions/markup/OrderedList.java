package markup;

public class OrderedList extends List {
    public OrderedList(java.util.List<MarkupSerializable> contentData) throws NullPointerException {
        super(contentData, "=1");
    }

    public OrderedList(MarkupSerializable contentData) throws NullPointerException {
        this(java.util.List.of(contentData));
    }

    public static void main(String[] args) {
        // :NOTE: Не должно компилироваться
        new OrderedList(java.util.List.of(new Text("hello")));
    }
}
