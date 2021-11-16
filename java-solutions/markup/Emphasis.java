package markup;

import md2html.MarkupCombinableParser;

import java.util.List;

public class Emphasis extends AbstractMarkupTaggedGroup implements MarkupCombinable {
    private static final Tag TAG = new Tag("*", "_", "[i]", "[/i]", "<em>", "</em>");

    public Emphasis(List<MarkupCombinable> content) {
        super(content, TAG);
    }

    public Emphasis(MarkupCombinable content) {
        super(content, TAG);
    }

    public static Emphasis parseMD(String data, MutableRange range) {
        MutableRange newRange = tryParseByTag(data, range, TAG.getMd(), TAG.getMd());
        if (newRange == null) {
            newRange = tryParseByTag(data, range, TAG.getMdAlt(), TAG.getMdAlt());
        }
        if (newRange == null) {
            return null;
        }
        return new Emphasis(MarkupCombinableParser.getInstance().parseMD(data, newRange));
    }

    public static boolean isValidLeftBorder(String data, int pos) {
        return validateLeftBorderMDImpl(data, pos, TAG.getMd()) || validateLeftBorderMDImpl(data, pos, TAG.getMdAlt());
    }
}
