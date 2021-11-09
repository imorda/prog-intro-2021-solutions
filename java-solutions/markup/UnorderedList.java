package markup;

import java.util.List;

public class UnorderedList extends AbstractMarkupList {
    public UnorderedList(final List<ListItem> content) {
        super(content, Tags.UNORDERED_LIST);
    }

    public UnorderedList(final ListItem content) {
        super(content, Tags.UNORDERED_LIST);
    }
}
