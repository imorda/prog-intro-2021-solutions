package md2html;

import markup.*;

public class MarkupCombinableParser extends AbstractMarkupParser<MarkupCombinable> {
    private static MarkupCombinableParser singleton;

    private MarkupCombinableParser() {
    }

    public static MarkupCombinableParser getInstance() {
        if (singleton == null) {
            singleton = new MarkupCombinableParser();
        }
        return singleton;
    }

    public static boolean isMarkupElementMD(String data, int pos) {
        return Emphasis.isValidLeftBorder(data, pos) ||
                Strong.isValidLeftBorder(data, pos) ||
                Strikeout.isValidLeftBorder(data, pos) ||
                Code.isValidLeftBorder(data, pos);
    }

    @Override
    protected MarkupCombinable parseOneElementMD(String data, MutableRange range) {
        MarkupCombinable result;
        result = Strong.parseMD(data, range);
        if (result == null) {
            result = Emphasis.parseMD(data, range);
        }
        if (result == null) {
            result = Strikeout.parseMD(data, range);
        }
        if (result == null) {
            result = Code.parseMD(data, range);
        }
        if (result == null) {
            result = Text.parseMD(data, range);
        }
        return result;
    }
}
