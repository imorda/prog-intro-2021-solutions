package markup;

import java.util.List;

public class OrderedList extends MarkupList {
    public OrderedList(List<ListItem> content) {
        super(content, Tags.ORDERED_LIST);
    }

    public OrderedList(ListItem content) {
        super(content, Tags.ORDERED_LIST);
    }
}
