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
        if (range.get() >= data.length()) {
            return null;
        }


        for (ParseableTypes i : ParseableTypes.taggedCombinableTypes) {
            List<MarkupCombinable> result = new ArrayList<>();
            String detectedTag = detectLeftBorder(data, range, i.mdTag());
            try {
                if (detectedTag != null) {
                    range.set(range.get() + detectedTag.length());

                    if (i.type() == Unformatted.class) {
                        Unformatted parsed = parseUnformatted(data, range, detectedTag);
                        if (parsed == null) {
                            continue;
                        }

                        return parsed;
                    }

                    while (range.get() < data.length() && !validateRightBorder(data, range, detectedTag)) {
                        result.add(parseMD(data, range));
                    }
                    range.set(range.get() + detectedTag.length());
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
            if (data.charAt(range.get()) != '\\') {
                textBuilder.append(data.charAt(range.get()));
            }
            range.set(range.get() + 1);
        } while (range.get() < data.length() && !isTagSymbol(data, range) &&
                !(data.startsWith("\r\n\r\n", range.get()) || data.startsWith("\n\n", range.get())));

        if (range.get() >= data.length()) {
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
                if (pos.get() < data.length() && data.startsWith(j, pos.get()) &&
                        !(pos.get() > 0 && data.charAt(pos.get() - 1) == '\\')) {
                    return true;
                }
            }
        }
        return false;
    }

    private static String detectLeftBorder(String data, MutableInt pos, String... tag) {
        for (String i : tag) {
            if (pos.get() < data.length() && data.startsWith(i, pos.get()) &&
                    !(pos.get() + i.length() < data.length() &&
                            ("\n\r ").contains(Character.toString(data.charAt(pos.get() + i.length())))) &&
                    !(pos.get() > 0 && data.charAt(pos.get() - 1) == '\\')) {
                return i;
            }
        }
        return null;
    }

    private static boolean validateRightBorder(String data, MutableInt pos, String... tag) {
        for (String i : tag) {
            if (pos.get() > 0 && pos.get() < data.length() && data.startsWith(i, pos.get()) &&
                    !("\n\r ").contains(Character.toString(data.charAt(pos.get() - 1))) &&
                    data.charAt(pos.get() - 1) != '\\') {

                for (ParseableTypes j : ParseableTypes.taggedCombinableTypes) {
                    for (String k : j.mdTag()) {
                        if (k.length() > i.length() && data.startsWith(k, pos.get())) {
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
        while (pos.get() < data.length()) {
            if (data.startsWith(tag, pos.get())) {
                pos.set(pos.get() + tag.length());
                return new Unformatted(List.of(new Text(result.toString())));
            }
            result.append(data.charAt(pos.get()));
            pos.set(pos.get() + 1);
        }
        pos.set(startPos.get());
        return null;
    }
}
