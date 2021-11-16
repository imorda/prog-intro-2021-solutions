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

    protected static boolean validateLeftBorderMDImpl(String data, int pos, String tag) {
        return data.startsWith(tag, pos) && data.length() > pos + tag.length() &&
                !("\n\r ").contains(Character.toString(data.charAt(pos + tag.length()))) &&
                !data.startsWith(tag, pos + tag.length()) &&
                !(pos > 0 && data.charAt(pos - 1) == '\\');
    }

    protected static boolean findRightBorderMDImpl(String data, MutableRange range, int offset, String... tag) {
        for (int i = range.from + offset; i < range.to; i++) {
            for (String j : tag) {
                if (data.startsWith(j, i)) {
                    if (!("\n\r ").contains(Character.toString(data.charAt(i - 1)))) {
                        range.to = i + j.length();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    protected static MutableRange tryParseByTag(String data, MutableRange range, String tagLeft, String tagRight) {
        if (validateLeftBorderMDImpl(data, range.from, tagLeft)) {
            if (findRightBorderMDImpl(data, range, tagRight.length(), tagRight)) {
                MutableRange newRange = range.copy();
                newRange.from += tagLeft.length();
                newRange.to -= tagRight.length();
                return newRange;
            }
        }
        return null;
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

    @Override
    protected void generateHTMLTagImpl(StringBuilder sb, boolean closing) {
        if (closing) {
            sb.append(tag.getHtmlClose());
        } else {
            sb.append(tag.getHtmlOpen());
        }
    }
}
