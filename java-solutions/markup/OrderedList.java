package markup;

import java.util.List;

public class OrderedList extends AbstractMarkupList {
    public OrderedList(List<ListItem> content) {
        super(content, Tags.ORDERED_LIST);
    }

    public OrderedList(final ListItem... content) {
        super(List.of(content), Tags.ORDERED_LIST);
    }
}
