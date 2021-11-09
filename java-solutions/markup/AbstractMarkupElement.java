package markup;

import java.util.List;

public abstract class AbstractMarkupElement extends AbstractMarkupTaggedGroup implements MarkupCombinable {
    public AbstractMarkupElement(List<MarkupCombinable> content, Tags tag) {
        super(content, tag);
    }

    public AbstractMarkupElement(MarkupCombinable content, Tags tag) {
        super(content, tag);
    }
}
