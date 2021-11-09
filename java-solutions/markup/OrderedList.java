package markup;

import java.util.List;

public class OrderedList extends AbstractMarkupList {
    private static final Tag TAG = new Tag("", "[list=1]", "[/list]");
    public OrderedList(List<ListItem> content) {
        super(content, TAG);
    }
    public OrderedList(ListItem content) {
        super(content, TAG);
    }
}
