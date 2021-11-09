package markup;

import java.util.List;

public class Emphasis extends AbstractMarkupElement {
    public Emphasis(MarkupCombinable content) {
        super(content, Tags.EMPHASIS);
    }

    public Emphasis(List<MarkupCombinable> content) {
        super(content, Tags.EMPHASIS);
    }
}
