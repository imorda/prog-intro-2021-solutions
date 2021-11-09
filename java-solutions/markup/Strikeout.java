package markup;

import java.util.List;

public class Strikeout extends AbstractMarkupElement {
    public Strikeout(MarkupCombinable content) {
            super(content, Tags.STRIKEOUT);
    }

    public Strikeout(List<MarkupCombinable> content) {
        super(content, Tags.STRIKEOUT);
    }
}
