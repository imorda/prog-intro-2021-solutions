package markup;

public interface MarkupSerializable {
    void toMarkdown(StringBuilder sb);

    void toBBCode(StringBuilder sb);
}
