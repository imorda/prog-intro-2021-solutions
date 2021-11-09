package markup;

import java.util.List;

public class Emphasis extends AbstractMarkupElement {
    private static final Tag TAG = new Tag("*", "i");
    public Emphasis(List<MarkupCombinable> content) {
        super(content, TAG);
    }
    public Emphasis(MarkupCombinable content) {
        super(content, TAG);
    }
}
