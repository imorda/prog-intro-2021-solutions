package md2html;

import markup.MarkupCombinable;
import markup.Text;
import markup.Unformatted;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class MarkupCombinableParser {

    private MarkupCombinableParser() {
    }

    public static MarkupCombinable parseMD(String data, MutableInt range) {
        if (range.val >= data.length()) {
            return null;
        }


        for (ParseableTypes i : ParseableTypes.taggedCombinableTypes) {
            List<MarkupCombinable> result = new ArrayList<>();
            String detectedTag = detectLeftBorder(data, range, i.mdTag());
            try {
                if (detectedTag != null) {
                    range.val += detectedTag.length();

                    if (i.type() == Unformatted.class) {
                        Unformatted parsed = parseUnformatted(data, range, detectedTag);
                        if (parsed == null) {
                            continue;
                        }

                        return parsed;
                    }

                    while (range.val < data.length() && !validateRightBorder(data, range, detectedTag)) {
                        result.add(parseMD(data, range));
                    }
                    range.val += detectedTag.length();
                    return i.type().getDeclaredConstructor(List.class).newInstance(result);
                }
            } catch (NoSuchMethodException e) {
                throw new ClassFormatError(i.type().getName() + " doesn't have a compatible constructor. "
                        + e.getMessage());
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new ClassFormatError(i.type().getName() + " couldn't be instantiated: "
                        + e.getMessage());
            }
        }

        StringBuilder textBuilder = new StringBuilder();

        do {
            if (data.charAt(range.val) != '\\') {
                textBuilder.append(data.charAt(range.val));
            }
            range.val++;
        } while (range.val < data.length() && !isTagSymbol(data, range) &&
                !(data.startsWith("\r\n\r\n", range.val) || data.startsWith("\n\n", range.val)));

        if (range.val >= data.length()) {
            while (textBuilder.length() > 0 &&
                    "\r\n".contains(textBuilder.subSequence(textBuilder.length() - 1, textBuilder.length()))) {
                textBuilder.deleteCharAt(textBuilder.length() - 1);
            }
        }
        return new Text(textBuilder.toString());
    }

    private static boolean isTagSymbol(String data, MutableInt pos) {
        for (ParseableTypes i : ParseableTypes.taggedCombinableTypes) {
            for (String j : i.mdTag()) {
                if (pos.val < data.length() && data.startsWith(j, pos.val) &&
                        !(pos.val > 0 && data.charAt(pos.val - 1) == '\\')) {
                    return true;
                }
            }
        }
        return false;
    }

    private static String detectLeftBorder(String data, MutableInt pos, String... tag) {
        for (String i : tag) {
            if (pos.val < data.length() && data.startsWith(i, pos.val) &&
                    !(pos.val + i.length() < data.length() &&
                            ("\n\r ").contains(Character.toString(data.charAt(pos.val + i.length())))) &&
                    !(pos.val > 0 && data.charAt(pos.val - 1) == '\\')) {
                return i;
            }
        }
        return null;
    }

    private static boolean validateRightBorder(String data, MutableInt pos, String... tag) {
        for (String i : tag) {
            if (pos.val > 0 && pos.val < data.length() && data.startsWith(i, pos.val) &&
                    !("\n\r ").contains(Character.toString(data.charAt(pos.val - 1))) &&
                    data.charAt(pos.val - 1) != '\\') {

                for (ParseableTypes j : ParseableTypes.taggedCombinableTypes) {
                    for (String k : j.mdTag()) {
                        if (k.length() > i.length() && data.startsWith(k, pos.val)) {
                            return false;
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    private static Unformatted parseUnformatted(String data, MutableInt pos, String tag) {
        MutableInt startPos = pos.copy();
        StringBuilder result = new StringBuilder();
        while (pos.val < data.length()) {
            if (data.startsWith(tag, pos.val)) {
                pos.val += tag.length();
                return new Unformatted(List.of(new Text(result.toString())));
            }
            result.append(data.charAt(pos.val));
            pos.val++;
        }
        pos.val = startPos.val;
        return null;
    }
}
