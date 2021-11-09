package markup;

import java.util.List;
import java.util.Objects;

public abstract class AbstractMarkupTaggedGroup extends AbstractMarkupGroup {
    protected final Tags tag;

    public AbstractMarkupTaggedGroup(List<? extends MarkupSerializable> content, Tags tag) {
        super(content);
        this.tag = Objects.requireNonNull(tag);
    }

    public AbstractMarkupTaggedGroup(MarkupSerializable content, Tags tag) {
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
