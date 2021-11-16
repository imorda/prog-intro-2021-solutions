package markup;

import md2html.MarkupCombinableParser;

import java.util.Objects;

public class Text implements MarkupCombinable {
    final private String content;

    public Text(String content) {
        this.content = Objects.requireNonNull(content);
    }

    public static Text parseMD(String data, MutableRange range) {
        for (int i = range.from; i < range.to; i++) {
            if (data.charAt(i) == '\\') {
                range.to = i + 1;
                return new Text(data.substring(range.from, range.to - 1));
            }
            if (MarkupCombinableParser.isMarkupElementMD(data, i) && i > range.from) {
                range.to = i;
            }
        }

        return new Text(data.substring(range.from, range.to));
    }

    private void toStringBuilder(StringBuilder sb) {
        sb.append(content);
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        toStringBuilder(sb);
    }

    @Override
    public void toBBCode(StringBuilder sb) {
        toStringBuilder(sb);
    }

    @Override
    public void toHtml(StringBuilder sb) {
        for (int i = 0; i < content.length(); i++) {
            char curChar = content.charAt(i);
            switch (curChar) {
                case '&':
                    sb.append("&amp;");
                    break;
                case '>':
                    sb.append("&gt;");
                    break;
                case '<':
                    sb.append("&lt;");
                    break;
                default:
                    sb.append(curChar);
                    break;
            }
        }
    }
}
