package markup;

import java.util.List;
import java.util.Objects;

public class MarkupSerializableSequence implements MarkupSerializable {
    private final List<MarkupSerializable> contentData;

    public MarkupSerializableSequence(List<MarkupSerializable> contentData)
            throws NullPointerException {
        this.contentData = List.copyOf(Objects.requireNonNull(contentData));
    }

    public MarkupSerializableSequence(MarkupSerializable contentData)
            throws NullPointerException {
        this(List.of(Objects.requireNonNull(contentData)));
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        for (MarkupSerializable i : contentData) {
            i.toMarkdown(sb);
        }
    }

    @Override
    public void toBBCode(StringBuilder sb) {
        for (MarkupSerializable i : contentData) {
            i.toBBCode(sb);
        }
    }
}
