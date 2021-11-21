package markup;

import java.util.List;

public class Paragraph extends AbstractMarkupTaggedGroup implements MarkupStructure {
    public Paragraph(List<MarkupCombinable> content) {
        super(content);
    }

    public Paragraph(MarkupCombinable content) {
        super(content);
    }

    @Override
    protected void generateMDTagImpl(StringBuilder sb, boolean closing) {
    }

    @Override
    protected void generateBBTagImpl(StringBuilder sb, boolean closing) {
    }

    @Override
    protected void generateHTMLTagImpl(StringBuilder sb, boolean closing) {
    }
}
