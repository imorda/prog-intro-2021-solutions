import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class m {
    public static void main(String[] args) {
        try (Reader in = new InputStreamReader(System.in)) {
            Scanner scanner = new Scanner(in, Scanner.TokenType.NUMBER_10);
            try {
                int t = Integer.parseInt(scanner.next());

                for (int test = 0; test < t; test++) {
                    int n = Integer.parseInt(scanner.next());
                    int[] days = new int[n];
                    for (int i = 0; i < n; i++) {
                        days[i] = Integer.parseInt(scanner.next());
                    }

                    int answer = 0;

                    Map<Integer, Integer> difficultyCounters = new HashMap<>();

                    for (int j = n - 1; j > 0; j--) {
                        for (int i = 0; i < j; i++) {
                            var curValue = difficultyCounters.get(2 * days[j] - days[i]);
                            if (curValue != null) {
                                answer += curValue;
                            }
                        }

                        var lastValue = difficultyCounters.getOrDefault(days[j], 0);

                        difficultyCounters.put(days[j], lastValue + 1);
                    }

                    System.out.println(answer);
                }
            } catch (NumberFormatException e) {
                System.err.println("Bad input format: " + e.getMessage());
            } finally {
                scanner.close();
            }
        } catch (IOException e) {
            System.err.println("Error reading input: " + e.getMessage());
        } catch (OutOfMemoryError e) {
            System.err.println("Not enough memory: " + e.getMessage());
        }
    }
}

class Scanner {
    public enum TokenType {
        WORD, NUMBER_10, NUMBER_16
    }

    private final BufferedReader reader;
    private final TokenType tokenType;

    private char curChar;
    private char prevChar;

    public Scanner(Reader reader, TokenType tokenType) throws IOException, NullPointerException {
        this.reader = new BufferedReader(Objects.requireNonNull(reader));
        this.tokenType = Objects.requireNonNull(tokenType);

        getNewChar();
    }

    public void close() throws IOException {
        reader.close();
    }

    public boolean hasNext() throws IOException {
        return hasNext(false);
    }

    public boolean hasNext(boolean stayInLine) throws IOException {
        while (true) {
            char x = getLastChar();
            if (stayInLine && isEOL()) {
                return false;
            }

            if (checkChar(x)) {
                return true;
            }

            if (!getNewChar()) {
                return false;
            }
        }
    }

    public boolean isEOL() throws IOException {
        return isEOL(false);
    }

    public boolean isEOL(boolean skipIfTrue) throws IOException {
        if (isLineSeparatorChar(getLastChar())) {
            if (curChar == '\n' && prevChar == '\r') {
                return false;
            }
            if (skipIfTrue) {
                return getNewChar();
            }
            return true;
        }
        return false;
    }

    public String next() throws IOException {
        return next(false);
    }

    public String next(boolean stayInLine) throws IOException {
        if (!hasNext(stayInLine)) {
            return null;
        }
        return extractToken();
    }

    public void skipLine() throws IOException {
        while (!isEOL()) {
            next();
        }
        getNewChar();
    }

    private boolean getNewChar() throws IOException {
        int readData = reader.read();
        if (readData < 0) {
            return false;
        }
        prevChar = curChar;
        curChar = Character.toLowerCase((char) readData);
        return true;
    }

    private char getLastChar() {
        return curChar;
    }

    private static boolean isWord(char x) {
        return Character.isLetter(x) || x == '\'' || Character.getType(x) == Character.DASH_PUNCTUATION;
    }

    private static boolean isNumber10(char x) {
        return Character.isDigit(x) || x == '-';
    }

    private static boolean isNumber16(char x) {
        return x == '-' || x == 'x' || Character.digit(x, 16) >= 0;
    }

    private static boolean isLineSeparatorChar(char x) {
        return System.lineSeparator().indexOf(x) >= 0;
    }

    private boolean checkChar(char x) {
        switch (tokenType) {
            case WORD:
                return isWord(x);
            case NUMBER_10:
                return isNumber10(x);
            case NUMBER_16:
                return isNumber16(x);
            default:
                return false;
        }
    }

    private String extractToken() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        while (checkChar(getLastChar())) {
            stringBuilder.append(getLastChar());
            if (!getNewChar()) {
                break;
            }
        }
        return stringBuilder.toString();
    }
}