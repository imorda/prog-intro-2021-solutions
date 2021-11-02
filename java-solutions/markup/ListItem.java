package markup;

import java.util.List;


public class ListItem extends MarkupClass {
    public ListItem(List<MarkupSerializable> contentData) throws NullPointerException {
        super(contentData, "", "", "[*]", "");
    }

    public ListItem(MarkupSerializable contentData) throws NullPointerException {
        this(List.of(contentData));
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        throw new UnsupportedOperationException("Markdown is unsupported for ListItem");
    }

    @Override
    public void toBBCode(StringBuilder sb) {
        super.toBBCode(sb);
    }
}
