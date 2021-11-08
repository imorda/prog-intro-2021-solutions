package markup;

import java.util.List;

public class ListItem extends MarkupTaggedGroup implements MarkupSerializable {
    public ListItem(List<MarkupStructure> content) {
        super(content, Tags.LIST_ITEM);
    }

    public ListItem(MarkupStructure content) {
        super(content, Tags.LIST_ITEM);
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        throw new UnsupportedOperationException("Markdown is unsupported for Lists");
    }
}
