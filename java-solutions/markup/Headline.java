package markup;

import java.util.List;

public class Headline extends AbstractMarkupTaggedGroup implements MarkupStructure {
    private final int level;

    public Headline(List<MarkupCombinable> content, int level) {
        super(content);
        this.level = level;
    }

    public Headline(MarkupCombinable content, int level) {
        this(List.of(content), level);
    }

    public int getLevel() {
        return level;
    }

    @Override
    protected void generateMDTagImpl(StringBuilder sb, boolean closing) {
        throw new UnsupportedOperationException("Markdown is unsupported for 'Headline'");
    }

    @Override
    protected void generateBBTagImpl(StringBuilder sb, boolean closing) {
        throw new UnsupportedOperationException("BB code is unsupported for 'Headline'");
    }

    @Override
    protected void generateHTMLTagImpl(StringBuilder sb, boolean closing) {
        sb.append(closing ? "</h" + level + ">" + System.lineSeparator() : "<h" + level + ">");
    }
}
