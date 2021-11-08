package markup;

import java.util.List;
import java.util.Objects;

public class MarkupTaggedGroup extends AbstractMarkupGroup {
    protected final Tags tag;

    public MarkupTaggedGroup(List<? extends MarkupSerializable> content, Tags tag) {
        super(content);
        this.tag = Objects.requireNonNull(tag);
    }

    public MarkupTaggedGroup(MarkupSerializable content, Tags tag) {
        super(content);
        this.tag = Objects.requireNonNull(tag);
    }

    @Override
    protected void generateMDTagImpl(StringBuilder sb, boolean closing) {
        sb.append(tag.md);
    }

    @Override
    protected void generateBBTagImpl(StringBuilder sb, boolean closing) {
        if (closing) {
            sb.append(tag.bbClose);
        } else {
            sb.append(tag.bbOpen);
        }
    }
}
