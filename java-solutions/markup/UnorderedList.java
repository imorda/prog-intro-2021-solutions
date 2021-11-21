package markup;

import java.util.List;

public class UnorderedList extends AbstractMarkupList {
    public UnorderedList(List<ListItem> content) {
        super(content);
    }

    public UnorderedList(ListItem content) {
        super(content);
    }

    @Override
    protected void generateBBTagImpl(StringBuilder sb, boolean closing) {
        sb.append(closing ? "[/list]" : "[list]");
    }
}
