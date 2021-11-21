package markup;

import java.util.List;

public class Document extends AbstractMarkupGroup implements MarkupSerializable {
    public Document(List<MarkupStructure> content) {
        super(content);
    }

    public Document(MarkupStructure content) {
        super(content);
    }
}
