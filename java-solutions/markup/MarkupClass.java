package markup;

import java.util.List;
import java.util.Objects;

public class MarkupClass implements MarkdownSerializable {
    private final String highlighter;
    private final List<MarkdownSerializable> contentData;

    public MarkupClass(List<MarkdownSerializable> contentData, final String highlighter) throws NullPointerException {
        this.highlighter = Objects.requireNonNull(highlighter);
        this.contentData = List.copyOf(Objects.requireNonNull(contentData));
    }

    public MarkupClass(MarkdownSerializable contentData, final String highlighter) throws NullPointerException {
        this(List.of(Objects.requireNonNull(contentData)), highlighter);
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        sb.append(highlighter);
        for (MarkdownSerializable i : contentData) {
            i.toMarkdown(sb);
        }
        sb.append(highlighter);
    }
}
