package markup;

import java.util.List;
import java.util.Objects;

public abstract class AbstractMarkupGroup implements MarkupSerializable {
    protected final List<MarkupSerializable> content;

    public AbstractMarkupGroup(List<? extends MarkupSerializable> content) {
        this.content = List.copyOf(Objects.requireNonNull(content));
    }

    public AbstractMarkupGroup(MarkupSerializable content) {
        this(List.of(Objects.requireNonNull(content)));
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        for (MarkupSerializable i : content) {
            i.toMarkdown(sb);
        }
    }

    @Override
    public void toBBCode(StringBuilder sb) {
        for (MarkupSerializable i : content) {
            i.toBBCode(sb);
        }
    }

    @Override
    public void toHtml(StringBuilder sb) {
        for (MarkupSerializable i : content) {
            i.toHtml(sb);
        }
    }
}
