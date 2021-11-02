package markup;

import java.util.Objects;

public class Text implements MarkupSerializable {
    private final String value;

    public Text(String value) throws NullPointerException {
        this.value = Objects.requireNonNull(value);
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        sb.append(value);
    }

    @Override
    public void toBBCode(StringBuilder sb) {
        sb.append(value);
    }
}
