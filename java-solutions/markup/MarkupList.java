package markup;

import java.util.List;

public class MarkupList extends MarkupTaggedGroup implements MarkupStructure {
    public MarkupList(List<ListItem> content, Tags tag) {
        super(content, tag);
    }

    public MarkupList(ListItem content, Tags tag) {
        super(content, tag);
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        throw new UnsupportedOperationException("Markdown is unsupported for Lists");
    }
}
