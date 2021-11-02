package markup;

import java.util.List;

public class Paragraph extends MarkupClass {
    public Paragraph(List<MarkupSerializable> contentData) {
        super(contentData, "", "", "", "");
    }

    public Paragraph(MarkupSerializable contentData) {
        super(contentData, "", "", "", "");
    }
}