package markup;

import java.util.List;
import java.util.Objects;

public class MarkupClass extends MarkupSerializableSequence {
    // :NOTE: Память
    private final String mdHighlighterOpen;
    private final String mdHighlighterClose;
    private final String bbHighlighterOpen;
    private final String bbHighlighterClose;

    public MarkupClass(List<MarkupSerializable> contentData, final String mdHighlighterOpen,
                       final String mdHighlighterClose, final String bbHighlighterOpen, final String bbHighlighterClose)
            throws NullPointerException {
        super(contentData);
        this.mdHighlighterOpen = Objects.requireNonNull(mdHighlighterOpen);
        this.mdHighlighterClose = Objects.requireNonNull(mdHighlighterClose);
        this.bbHighlighterOpen = Objects.requireNonNull(bbHighlighterOpen);
        this.bbHighlighterClose = Objects.requireNonNull(bbHighlighterClose);
    }

    public MarkupClass(MarkupSerializable contentData, final String mdHighlighterOpen,
                       final String mdHighlighterClose, final String bbHighlighterOpen, final String bbHighlighterClose)
            throws NullPointerException {
        this(List.of(Objects.requireNonNull(contentData)), mdHighlighterOpen, mdHighlighterClose,
                bbHighlighterOpen, bbHighlighterClose);
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        sb.append(mdHighlighterOpen);
        super.toMarkdown(sb);
        sb.append(mdHighlighterClose);
    }

    @Override
    public void toBBCode(StringBuilder sb) {
        sb.append(bbHighlighterOpen);
        super.toBBCode(sb);
        sb.append(bbHighlighterClose);
    }
}
