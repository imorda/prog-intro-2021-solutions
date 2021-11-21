package markup;

import java.util.List;

public class Emphasis extends AbstractMarkupTaggedGroup implements MarkupCombinable {
    public Emphasis(List<MarkupCombinable> content) {
        super(content);
    }

    public Emphasis(MarkupCombinable content) {
        super(content);
    }

    @Override
    protected void generateMDTagImpl(StringBuilder sb, boolean closing) {
        sb.append("*");
    }

    @Override
    protected void generateBBTagImpl(StringBuilder sb, boolean closing) {
        sb.append(closing ? "[/i]" : "[i]");
    }

    @Override
    protected void generateHTMLTagImpl(StringBuilder sb, boolean closing) {
        sb.append(closing ? "</em>" : "<em>");
    }
}
