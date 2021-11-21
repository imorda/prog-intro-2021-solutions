package markup;

import java.util.List;

public class ListItem extends AbstractMarkupTaggedGroup implements MarkupSerializable {
    public ListItem(List<MarkupStructure> content) {
        super(content);
    }

    public ListItem(MarkupStructure content) {
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
        throw new UnsupportedOperationException("Markdown is unsupported for Lists");
    }

    @Override
    protected void generateBBTagImpl(StringBuilder sb, boolean closing) {
        sb.append(closing ? "" : "[*]");
    }

    @Override
    protected void generateHTMLTagImpl(StringBuilder sb, boolean closing) {
        throw new UnsupportedOperationException("HTML is unsupported for Lists");
    }
}
