package markup;

import java.util.List;

public class MarkupElement extends MarkupTaggedGroup implements MarkupCombinable {
    public MarkupElement(List<MarkupCombinable> content, Tags tag) {
        super(content, tag);
    }

    public MarkupElement(MarkupCombinable content, Tags tag) {
        super(content, tag);
    }
}
