package markup;

import java.util.List;

public class ListItem extends AbstractMarkupTaggedGroup implements MarkupSerializable {
    private static final Tag TAG = new Tag("", "[*]", "");

    public ListItem(List<MarkupStructure> content) {
        super(content, TAG);
    }

    public ListItem(MarkupStructure content) {
        super(content, TAG);
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        throw new UnsupportedOperationException("Markdown is unsupported for Lists");
    }
}
