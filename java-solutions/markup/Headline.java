package markup;

import md2html.MarkupCombinableParser;

import java.util.List;

public class Headline extends AbstractMarkupTaggedGroup implements MarkupStructure {
    private final int level;

    public Headline(List<MarkupCombinable> content, int level) {
        super(content, new Tag("",
                "", "",
                "<h" + level + ">", "</h" + level + ">" + System.lineSeparator()));
        this.level = level;
    }

    public Headline(MarkupCombinable content, int level) {
        this(List.of(content), level);
    }

    public static Headline parseMD(String data, MutableRange range) {
        int level = 0;
        int currentPos = range.from;

        while (currentPos < data.length() && data.charAt(currentPos) == '#') {
            currentPos++;
            level++;
        }

        if (level == 0) {
            return null;
        }

        if (currentPos >= data.length() || data.charAt(currentPos) != ' ') {
            return null;
        }
        currentPos++;

        findRightBorderMDImpl(data, range, currentPos - range.from, "\n\n", "\r\n\r\n");

        MutableRange newRange = range.copy();
        newRange.from = currentPos;
        while (data.charAt(newRange.to - 1) == '\n' || data.charAt(newRange.to - 1) == '\r') {
            newRange.to--;
        }

        return new Headline(MarkupCombinableParser.getInstance().parseMD(data, newRange), level);
    }

    public int getLevel() {
        return level;
    }
}
