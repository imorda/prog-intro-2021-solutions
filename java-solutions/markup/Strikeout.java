package markup;

import java.util.List;

public class Strikeout extends AbstractMarkupTaggedGroup implements MarkupCombinable {
    public Strikeout(List<MarkupCombinable> content) {
        super(content);
    }

    public Strikeout(MarkupCombinable content) {
        super(content);
    }

    @Override
    protected void generateMDTagImpl(StringBuilder sb, boolean closing) {
        sb.append("~");
    }

    @Override
    protected void generateBBTagImpl(StringBuilder sb, boolean closing) {
        sb.append(closing ? "[/s]" : "[s]");
    }

    @Override
    protected void generateHTMLTagImpl(StringBuilder sb, boolean closing) {
        sb.append(closing ? "</s>" : "<s>");
    }
}
