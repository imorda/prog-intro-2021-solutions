import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class WordStatWords {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Invalid arguments count, got " + args.length + " arguments");
            return;
        }

        Map<String, Integer> data = new TreeMap<>();
        try {
            Reader in = new FileReader(args[0], StandardCharsets.UTF_8);
            try {
                Scanner scanner = new Scanner(in, Scanner.TokenType.WORD);
                while (scanner.hasNext()) {
                    incrementValue(data, scanner.next());
                }
            } finally {
                in.close();
            }

            Writer out = new BufferedWriter(new FileWriter(args[1], StandardCharsets.UTF_8));
            try {
                for (Map.Entry<String, Integer> i : data.entrySet()) {
                    out.write(i.getKey());
                    out.write(" ");
                    out.write(i.getValue().toString());
                    out.write(System.lineSeparator());
                }
            } finally {
                out.close();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Input file not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
        } catch (OutOfMemoryError e) {
            System.err.println("Not enough memory: " + e.getMessage());
        }
    }

    private static void incrementValue(Map<String, Integer> treeMap, String key) throws NullPointerException {
        Objects.requireNonNull(key);

        if (!key.isEmpty()) {
            key = key.toLowerCase();
            if (treeMap.containsKey(key)) {
                treeMap.put(key, treeMap.get(key) + 1);
            } else {
                treeMap.put(key, 1);
            }
        }
    }
}
