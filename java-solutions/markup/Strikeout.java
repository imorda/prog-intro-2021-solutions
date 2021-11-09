package markup;

import java.util.List;

public class Strikeout extends AbstractMarkupElement {
    private static final Tag TAG = new Tag("~", "s");
    public Strikeout(List<MarkupCombinable> content) {
        super(content, TAG);
    }
    public Strikeout(MarkupCombinable content) {
        super(content, TAG);
    }
}
