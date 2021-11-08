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

    protected abstract void generateMDTagImpl(StringBuilder sb, boolean closing);

    protected abstract void generateBBTagImpl(StringBuilder sb, boolean closing);

    @Override
    public void toMarkdown(StringBuilder sb) {
        generateMDTagImpl(sb, false);
        for (MarkupSerializable i : content) {
            i.toMarkdown(sb);
        }
        generateMDTagImpl(sb, true);
    }

    @Override
    public void toBBCode(StringBuilder sb) {
        generateBBTagImpl(sb, false);
        for (MarkupSerializable i : content) {
            i.toBBCode(sb);
        }
        generateBBTagImpl(sb, true);
    }
}
