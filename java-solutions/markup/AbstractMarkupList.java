package markup;

import java.util.List;

public abstract class AbstractMarkupList extends AbstractMarkupTaggedGroup implements MarkupStructure {
    public AbstractMarkupList(List<ListItem> content, Tag tag) {
        super(content, tag);
    }

    public AbstractMarkupList(ListItem content, Tag tag) {
        super(content, tag);
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        throw new UnsupportedOperationException("Markdown is unsupported for Lists");
    }

    @Override
    public void toHtml(StringBuilder sb) {
        throw new UnsupportedOperationException("HTML is unsupported for Lists");
    }
}
