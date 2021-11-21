package markup;

import java.util.List;

public class Code extends AbstractMarkupTaggedGroup implements MarkupCombinable {
    public Code(List<MarkupCombinable> content) {
        super(content);
    }

    public Code(MarkupCombinable content) {
        super(content);
    }

    @Override
    protected void generateMDTagImpl(StringBuilder sb, boolean closing) {
        sb.append("`");
    }

    @Override
    protected void generateBBTagImpl(StringBuilder sb, boolean closing) {
        throw new UnsupportedOperationException("BB tag unsupported for 'Code'");
    }

    @Override
    protected void generateHTMLTagImpl(StringBuilder sb, boolean closing) {
        sb.append(closing ? "</code>" : "<code>");
    }
}
