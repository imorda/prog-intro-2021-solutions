package markup;

import md2html.MarkupCombinableParser;

import java.util.List;

public class Strong extends AbstractMarkupTaggedGroup implements MarkupCombinable {
    private static final Tag TAG = new Tag("__", "**", "[b]", "[/b]", "<strong>", "</strong>");

    public Strong(List<MarkupCombinable> content) {
        super(content, TAG);
    }

    public Strong(MarkupCombinable content) {
        super(content, TAG);
    }

    public static Strong parseMD(String data, MutableRange range) {
        MutableRange newRange = tryParseByTag(data, range, TAG.getMd(), TAG.getMd());
        if (newRange == null) {
            newRange = tryParseByTag(data, range, TAG.getMdAlt(), TAG.getMdAlt());
        }
        if (newRange == null) {
            return null;
        }
        return new Strong(MarkupCombinableParser.getInstance().parseMD(data, newRange));
    }

    public static boolean isValidLeftBorder(String data, int pos) {
        return validateLeftBorderMDImpl(data, pos, TAG.getMd()) || validateLeftBorderMDImpl(data, pos, TAG.getMdAlt());
    }
}
