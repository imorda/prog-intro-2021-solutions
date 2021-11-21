package markup;

import java.util.List;

public class Strong extends AbstractMarkupTaggedGroup implements MarkupCombinable {
    public Strong(List<MarkupCombinable> content) {
        super(content);
    }

    public Strong(MarkupCombinable content) {
        super(content);
    }

    @Override
    protected void generateMDTagImpl(StringBuilder sb, boolean closing) {
        sb.append("__");
    }

    @Override
    protected void generateBBTagImpl(StringBuilder sb, boolean closing) {
        sb.append(closing ? "[/b]" : "[b]");
    }

    @Override
    protected void generateHTMLTagImpl(StringBuilder sb, boolean closing) {
        sb.append(closing ? "</strong>" : "<strong>");
    }
}
