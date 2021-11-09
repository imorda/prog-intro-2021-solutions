package markup;

import java.util.List;

public class UnorderedList extends AbstractMarkupList {
    private static final Tag TAG = new Tag("", "list");
    public UnorderedList(List<ListItem> content) {
        super(content, TAG);
    }
    public UnorderedList(ListItem content) {
        super(content, TAG);
    }
}
