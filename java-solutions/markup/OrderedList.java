package markup;

import java.util.List;

public class OrderedList extends AbstractMarkupList {
    public OrderedList(List<ListItem> content) {
        super(content);
    }

    public OrderedList(ListItem content) {
        super(content);
    }

    @Override
    protected void generateBBTagImpl(StringBuilder sb, boolean closing) {
        sb.append(closing ? "[/list]" : "[list=1]");
    }
}
