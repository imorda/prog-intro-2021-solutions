package markup;

import java.util.List;

public class UnorderedList extends MarkupList {
    public UnorderedList(List<ListItem> content) {
        super(content, Tags.UNORDERED_LIST);
    }

    public UnorderedList(ListItem content) {
        super(content, Tags.UNORDERED_LIST);
    }
}