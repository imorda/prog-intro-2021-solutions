import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class WsppSecondG {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Invalid arguments count, got " + args.length + " arguments");
            return;
        }

        Map<String, WordData> data = new LinkedHashMap<>();
        try {
            try (Reader in = new FileReader(args[0], StandardCharsets.UTF_8)) {
                Scanner scanner = new Scanner(in, Scanner.TokenType.WORD);
                int currentWordPosition = 1;
                do {
                    Set<String> parityInLine = new LinkedHashSet<>();
                    while (scanner.hasNext(true)) {
                        String key = scanner.next(true);
                        if (key == null) {
                            continue;
                        }

                        var element = data.get(key);

                        if (element == null) {
                            data.put(key, new WordData());
                            element = data.get(key);
                        }

                        element.occurenceCount++;
                        if (parityInLine.contains(key)) {
                            element.occurenceList.add(currentWordPosition);
                            parityInLine.remove(key);
                        } else {
                            parityInLine.add(key);
                        }

                        currentWordPosition++;
                    }
                } while (scanner.isEOL(true));
            }

            try (Writer out = new BufferedWriter(new FileWriter(args[1], StandardCharsets.UTF_8))) {
                for (Map.Entry<String, WordData> i : data.entrySet()) {
                    WordData wordData = i.getValue();
                    out.write(i.getKey() + " " + wordData.occurenceCount);
                    for (int j = 0; j < wordData.occurenceList.length(); j++) {
                        out.write(" " + wordData.occurenceList.get(j));
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

class WordData {
    public int occurenceCount = 0;
    public final IntList occurenceList;

    public WordData() {
        occurenceList = new IntList();
    }
}