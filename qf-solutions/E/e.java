import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.Objects;


public class e {
    public static void main(String[] args) {
        try (Reader in = new InputStreamReader(System.in)) {
            Scanner scanner = new Scanner(in, Scanner.TokenType.NUMBER_10);
            try {
                int n = Integer.parseInt(scanner.next());
                int m = Integer.parseInt(scanner.next());

                IntList[] graph = new IntList[n];

                for (int i = 0; i < n; i++) {
                    graph[i] = new IntList();
                }

                for (int i = 0; i < n - 1; i++) {
                    int v = Integer.parseInt(scanner.next()) - 1;
                    int u = Integer.parseInt(scanner.next()) - 1;

                    graph[v].add(u);
                    graph[u].add(v);
                }

                boolean[] teamCities = new boolean[n];
                int cityWithTeam = -1;
                for (int i = 0; i < m; i++) {
                    int c = Integer.parseInt(scanner.next()) - 1;
                    teamCities[c] = true;
                    cityWithTeam = c;
                }

                IntList pathToDeepest = new IntList();
                IntPair deepestTeam = dfsFindDeepest(cityWithTeam, -1, graph, 0, teamCities, pathToDeepest);

                int potentialCity = deepestTeam.second;
                if (dfsCheckAnswer(potentialCity, -1,
                        graph, 0, (deepestTeam.first + 1) / 2, teamCities)) {
                    System.out.println("YES");
                    System.out.println(potentialCity + 1);
                } else {
                    System.out.println("NO");
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

    static IntPair dfsFindDeepest(int node, int prevNode, IntList[] graph, int curDepth,
                                  boolean[] teamCities, IntList path) {
        path.add(node);
        IntPair deepestChild = new IntPair(-1, -1);
        if (teamCities[node]) {
            deepestChild = new IntPair(curDepth, path.get(path.length() / 2));
        }
        for (int i = 0; i < graph[node].length(); i++) {
            if (graph[node].get(i) != prevNode) {
                IntPair curChild = dfsFindDeepest(graph[node].get(i), node, graph, curDepth + 1, teamCities, path);
                if (deepestChild.first < curChild.first) {
                    deepestChild = curChild;
                }
            }
        }
        path.pop();

        return deepestChild;
    }

    static boolean dfsCheckAnswer(int node, int prevNode, IntList[] graph, int curDepth,
                                  int targetDepth, boolean[] teamCities) {
        if (teamCities[node] && curDepth != targetDepth) {
            return false;
        }
        for (int i = 0; i < graph[node].length(); i++) {
            if (graph[node].get(i) != prevNode) {
                if (!dfsCheckAnswer(graph[node].get(i), node, graph, curDepth + 1, targetDepth, teamCities)) {
                    return false;
                }
            }
        }
        return true;
    }
}

class Scanner {
    private final BufferedReader reader;
    private final TokenType tokenType;
    private char curChar;
    private char prevChar;

    public Scanner(Reader reader, TokenType tokenType) throws IOException, NullPointerException {
        this.reader = new BufferedReader(Objects.requireNonNull(reader));
        this.tokenType = Objects.requireNonNull(tokenType);

        getNewChar();
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
        curChar = (char) readData;
        return true;
    }

    private char getLastChar() {
        return curChar;
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

    public enum TokenType {
        WORD, NUMBER_10, NUMBER_16
    }
}

class IntList {
    private int[] buffer;
    private int size = 0;

    public IntList() {
        buffer = new int[10];
    }

    public IntList(int capacity) {
        buffer = new int[capacity];
        size = capacity;
    }

    public int get(int pos) throws IndexOutOfBoundsException {
        if (pos >= size) {
            throw new IndexOutOfBoundsException("Array index is out of bounds");
        }
        return buffer[pos];
    }

    public void set(int pos, int val) throws IndexOutOfBoundsException {
        if (pos >= size) {
            throw new IndexOutOfBoundsException("Array index is out of bounds");
        }
        buffer[pos] = val;
    }

    public void reserve(int capacity) throws OutOfMemoryError {
        if (capacity > buffer.length) {
            buffer = Arrays.copyOf(buffer, capacity);
        }
    }

    public void add(int value) throws OutOfMemoryError {
        if (size >= buffer.length) {
            reserve(buffer.length * 2 + 1);
        }
        size++;
        set(size - 1, value);
    }

    public void pop() {
        size = Math.max(0, size - 1);
    }

    public int length() {
        return size;
    }
}

class IntPair {
    int first, second;

    public IntPair(int first, int second) {
        this.first = first;
        this.second = second;
    }
}