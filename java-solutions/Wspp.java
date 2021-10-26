import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

public class Wspp {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Invalid arguments count, got " + args.length + " arguments");
            return;
        }

        Map<String, IntList> data = new LinkedHashMap<>();
        try {
            try (Reader in = new FileReader(args[0], StandardCharsets.UTF_8)) {
                Scanner scanner = new Scanner(in, Scanner.TokenType.WORD);
                int currentWordPosition = 1;
                while (scanner.hasNext()) {
                    String key = scanner.next();
                    if (key == null) {
                        continue;
                    }

                    if (!data.containsKey(key)) {
                        data.put(key, new IntList());
                    }
                    data.get(key).add(currentWordPosition);

                    currentWordPosition++;
                }
            }

            try (Writer out = new BufferedWriter(new FileWriter(args[1], StandardCharsets.UTF_8))) {
                for (Map.Entry<String, IntList> i : data.entrySet()) {
                    IntList list = i.getValue();
                    out.write(i.getKey() + " " + list.length());
                    for (int j = 0; j < list.length(); j++) {
                        out.write(" " + list.get(j));
                    }
                    out.write(System.lineSeparator());
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Input file not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
        } catch (OutOfMemoryError e) {
            System.err.println("Not enough memory: " + e.getMessage());
        }
    }
}
