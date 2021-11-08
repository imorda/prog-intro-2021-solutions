package markup;

import java.util.Objects;

public class Text implements MarkupCombinable {
    final private String content;

    public Text(String content) {
        this.content = Objects.requireNonNull(content);
    }

    private void toStringBuilder(StringBuilder sb) {
        sb.append(content);
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        toStringBuilder(sb);
    }

    @Override
    public void toBBCode(StringBuilder sb) {
        toStringBuilder(sb);
    }
}
