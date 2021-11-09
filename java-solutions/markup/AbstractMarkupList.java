package markup;

import java.util.List;

public abstract class AbstractMarkupList extends AbstractMarkupTaggedGroup implements MarkupStructure {
    public AbstractMarkupList(final List<ListItem> content, final Tags tag) {
        super(content, tag);
    }

    public AbstractMarkupList(final ListItem content, final Tags tag) {
        super(content, tag);
    }

    @Override
    public void toMarkdown(final StringBuilder sb) {
        throw new UnsupportedOperationException("Markdown is unsupported for Lists");
    }
}
