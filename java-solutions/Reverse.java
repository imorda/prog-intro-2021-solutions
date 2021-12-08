import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Reverse {
    public static void main(String[] args) {
        List<IntList> data = new ArrayList<>();
        try {
            Reader in = new InputStreamReader(System.in);
            try {
                Scanner inputScanner = new Scanner(in, Scanner.TokenType.NUMBER_10);
                try {
                    do {
                        data.add(new IntList());
                        while (inputScanner.hasNext(true)) {
                            try {
                                data.get(data.size() - 1).add(Integer.parseInt(inputScanner.next(true)));
                            } catch (NumberFormatException e) {
                                System.err.println("Number format error: " + e.getMessage());
                            }
                        }
                    } while (inputScanner.isEOL(true));
                } finally {
                    inputScanner.close();
                }
            } finally {
                in.close();
            }

            for (int i = data.size() - 1; i >= 0; i--) {
                for (int j = data.get(i).length() - 1; j >= 0; j--) {
                    System.out.print(data.get(i).get(j));
                    System.out.print(" ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.err.println("Error reading input: " + e.getMessage());
        } catch (OutOfMemoryError e) {
            System.err.println("Not enough memory: " + e.getMessage());
        }
    }
}