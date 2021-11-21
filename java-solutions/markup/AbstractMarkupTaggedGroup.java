package markup;

import java.util.List;

public abstract class AbstractMarkupTaggedGroup extends AbstractMarkupGroup {
    public AbstractMarkupTaggedGroup(List<? extends MarkupSerializable> content) {
        super(content);
    }

    public AbstractMarkupTaggedGroup(MarkupSerializable content) {
        super(content);
    }

    protected abstract void generateMDTagImpl(StringBuilder sb, boolean closing);

    protected abstract void generateBBTagImpl(StringBuilder sb, boolean closing);

    protected abstract void generateHTMLTagImpl(StringBuilder sb, boolean closing);

    @Override
    public void toMarkdown(StringBuilder sb) {
        generateMDTagImpl(sb, false);
        super.toMarkdown(sb);
        generateMDTagImpl(sb, true);
    }

    @Override
    public void toBBCode(StringBuilder sb) {
        generateBBTagImpl(sb, false);
        super.toBBCode(sb);
        generateBBTagImpl(sb, true);
    }

    @Override
    public void toHtml(StringBuilder sb) {
        generateHTMLTagImpl(sb, false);
        super.toHtml(sb);
        generateHTMLTagImpl(sb, true);
    }
}
