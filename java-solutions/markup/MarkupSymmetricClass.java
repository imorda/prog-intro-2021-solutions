package markup;

import java.util.List;
import java.util.Objects;

public class MarkupSymmetricClass extends MarkupClass {
    private final String mdHighlighter;
    private final String bbHighlighter;

    public MarkupSymmetricClass(List<MarkupSerializable> contentData, final String mdHighlighter, final String bbHighlighter)
            throws NullPointerException {
        super(contentData, mdHighlighter, mdHighlighter,
                "[" + bbHighlighter + "]", "[/" + bbHighlighter + "]");
        this.mdHighlighter = Objects.requireNonNull(mdHighlighter);
        this.bbHighlighter = Objects.requireNonNull(bbHighlighter);
    }

    public MarkupSymmetricClass(MarkupSerializable contentData, final String mdHighlighter, final String bbHighlighter)
            throws NullPointerException {
        this(List.of(Objects.requireNonNull(contentData)), mdHighlighter, bbHighlighter);
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        super.toMarkdown(sb);
    }

    @Override
    public void toBBCode(StringBuilder sb) {
        super.toBBCode(sb);
    }
}
