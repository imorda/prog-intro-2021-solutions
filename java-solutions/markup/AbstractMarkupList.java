package markup;

import java.util.List;

public abstract class AbstractMarkupList extends AbstractMarkupTaggedGroup implements MarkupStructure {
    public AbstractMarkupList(List<ListItem> content) {
        super(content);
    }

    public AbstractMarkupList(ListItem content) {
        super(content);
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        throw new UnsupportedOperationException("Markdown is unsupported for Lists");
    }

    @Override
    public void toHtml(StringBuilder sb) {
        throw new UnsupportedOperationException("HTML is unsupported for Lists");
    }

    @Override
    protected void generateMDTagImpl(StringBuilder sb, boolean closing) {
        throw new UnsupportedOperationException("Markdown is unsupported for 'Unordered list'");
    }

    @Override
    protected void generateHTMLTagImpl(StringBuilder sb, boolean closing) {
        throw new UnsupportedOperationException("HTML is unsupported for 'Unordered list'");
    }
}
