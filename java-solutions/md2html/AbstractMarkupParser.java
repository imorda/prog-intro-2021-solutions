package md2html;

import markup.MutableRange;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMarkupParser<T> {
    public List<T> parseMD(String data, MutableRange range) {
        List<T> result = new ArrayList<>();
        MutableRange newRange = range.copy();

        while (newRange.from < range.to) {
            T element = parseOneElementMD(data, newRange);
            if (element != null) {
                result.add(element);
            }
            newRange.from = newRange.to;
            newRange.to = range.to;
        }

        return result;
    }

    protected abstract T parseOneElementMD(String data, MutableRange range);
}
