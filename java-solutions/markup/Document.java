package markup;

import md2html.MarkupStructureParser;

import java.util.List;

public class Document extends AbstractMarkupGroup implements MarkupSerializable {
    public Document(List<MarkupStructure> content) {
        super(content);
    }

    public Document(MarkupStructure content) {
        super(content);
    }

    public static Document parse(String data) {
        return new Document(MarkupStructureParser.getInstance().parseMD(data, new MutableRange(0, data.length())));
    }

    @Override
    protected void generateMDTagImpl(StringBuilder sb, boolean closing) {
    }

    @Override
    protected void generateBBTagImpl(StringBuilder sb, boolean closing) {
    }

    @Override
    protected void generateHTMLTagImpl(StringBuilder sb, boolean closing) {
    }
}
