package markup;

import java.util.List;
import java.util.Objects;

public abstract class AbstractMarkupTaggedGroup extends AbstractMarkupGroup {
    protected final Tag tag;

    public AbstractMarkupTaggedGroup(List<? extends MarkupSerializable> content, Tag tag) {
        super(content);
        this.tag = Objects.requireNonNull(tag);
    }

    public AbstractMarkupTaggedGroup(MarkupSerializable content, Tag tag) {
        super(content);
        this.tag = Objects.requireNonNull(tag);
    }

    @Override
    protected void generateMDTagImpl(StringBuilder sb, boolean closing) {
        sb.append(tag.getMd());
    }

    @Override
    protected void generateBBTagImpl(StringBuilder sb, boolean closing) {
        if (closing) {
            sb.append(tag.getBbClose());
        } else {
            sb.append(tag.getBbOpen());
        }
    }
}
