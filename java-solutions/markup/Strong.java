package markup;

import java.util.List;

public class Strong extends AbstractMarkupElement {
    private static final Tag TAG = new Tag("__", "b");
    public Strong(List<MarkupCombinable> content) {
        super(content, TAG);
    }
    public Strong(MarkupCombinable content) {
        super(content, TAG);
    }
}
