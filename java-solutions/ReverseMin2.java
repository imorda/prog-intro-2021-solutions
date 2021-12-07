import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class ReverseMin2 {
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

            IntList lastLineMins = new IntList();

            for (int i = 0; i < data.size() - 1; i++) {
                for (int j = 0; j < data.get(i).length(); j++) {
                    if (j > 0) {
                        data.get(i).set(j, Math.min(data.get(i).get(j), data.get(i).get(j - 1)));
                    }

                    if (lastLineMins.length() > j) {
                        data.get(i).set(j, Math.min(data.get(i).get(j), lastLineMins.get(j)));

                        lastLineMins.set(j, data.get(i).get(j));
                    } else {
                        lastLineMins.add(data.get(i).get(j));
                    }

                    System.out.print(data.get(i).get(j));
                    System.out.print(" ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.err.println("Error writing result: " + e.getMessage());
        } catch (OutOfMemoryError e) {
            System.err.println("Not enough memory: " + e.getMessage());
        }
    }
}