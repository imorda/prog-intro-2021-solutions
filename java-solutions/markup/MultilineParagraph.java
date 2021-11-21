package markup;

import java.util.List;

public class MultilineParagraph extends Paragraph {
    public MultilineParagraph(List<MarkupCombinable> content) {
        super(content);
    }

    public MultilineParagraph(MarkupCombinable content) {
        super(content);
    }

    @Override
    protected void generateHTMLTagImpl(StringBuilder sb, boolean closing) {
        sb.append(closing ? "</p>" + System.lineSeparator() : "<p>");
    }
}
