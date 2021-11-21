package markup;

import java.util.List;

public class Unformatted extends AbstractMarkupTaggedGroup implements MarkupCombinable {
    public Unformatted(List<Text> content) {
        super(content);
    }

    public Unformatted(Text content) {
        super(content);
    }

    @Override
    protected void generateMDTagImpl(StringBuilder sb, boolean closing) {
        sb.append("```");
    }

    @Override
    protected void generateBBTagImpl(StringBuilder sb, boolean closing) {
        throw new UnsupportedOperationException("BB code in unsupported for 'Unformatted'");
    }

    @Override
    protected void generateHTMLTagImpl(StringBuilder sb, boolean closing) {
        sb.append(closing ? "</pre>" : "<pre>");
    }
}
