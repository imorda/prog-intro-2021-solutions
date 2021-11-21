package md2html;

import markup.Document;
import markup.MarkupStructure;

import java.util.ArrayList;
import java.util.List;

public class MarkupParser {
    private MarkupParser() {
    }

    public static Document parseMarkdown(String data) {
        MutableInt pos = new MutableInt(0);

        List<MarkupStructure> result = new ArrayList<>();

        while (pos.val < data.length()) {
            MarkupStructure struct = MarkupStructureParser.parseMD(data, pos);

            if (struct == null) {
                pos.val++;
            } else {
                result.add(struct);
            }
        }

        return new Document(result);
    }
}