package md2html;

import markup.Document;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Md2Html {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Invalid arguments count, got " + args.length + " arguments");
            return;
        }

        Document parsed;
        try {
            String input = Files.readString(Paths.get(args[0]), StandardCharsets.UTF_8);
            parsed = Document.parse(input);
        } catch (IOException e) {
            System.err.println("Could not read from input file: " + e.getMessage());
            return;
        }

        StringBuilder serialized = new StringBuilder();
        parsed.toHtml(serialized);
        try (Writer writer = new BufferedWriter(new FileWriter(args[1], StandardCharsets.UTF_8))) {
            writer.append(serialized);
        } catch (IOException e) {
            System.err.println("Could not write to output file: " + e.getMessage());
        }
    }
}
