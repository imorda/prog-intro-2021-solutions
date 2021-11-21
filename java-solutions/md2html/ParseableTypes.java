package md2html;

import markup.*;

import java.util.List;

public record ParseableTypes(Class<? extends MarkupCombinable> type, String... mdTag) {
    public static List<ParseableTypes> taggedCombinableTypes = List.of(
            new ParseableTypes(Strong.class, "__", "**"),
            new ParseableTypes(Unformatted.class, "```"),
            new ParseableTypes(Strikeout.class, "--"),
            new ParseableTypes(Emphasis.class, "*", "_"),
            new ParseableTypes(Code.class, "`"));
}