package markup;

import java.util.List;

public class Strong extends MarkupElement {
    public Strong(MarkupCombinable content) {
        super(content, Tags.STRONG);
    }

    public Strong(List<MarkupCombinable> content) {
        super(content, Tags.STRONG);
    }
}
