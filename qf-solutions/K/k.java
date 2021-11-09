import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class k {
    public static void main(String[] args) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            try (Scanner scanner = new Scanner(in.readLine())) {
                int n = scanner.nextInt();
                int m = scanner.nextInt();

                List<Province> provinces = new ArrayList<>();

                Province favourite = null;

                char[][] textInput = new char[n][];

                for (int i = 0; i < n; i++) {
                    String line = in.readLine();
                    textInput[i] = line.toCharArray();
                    for (int j = 0; j < m; j++) {
                        if (Character.isAlphabetic(line.charAt(j))) {
                            Province province = new Province(line.charAt(j), j, i);
                            provinces.add(province);
                            if (province.idChar == 'A') {
                                favourite = province;
                            }
                        }
                    }
                }

                assert favourite != null;

                // Find left-right limits
                Bounds horizontalLimits = expandHorizontally(favourite, provinces, m);

                // Find all nodes at sides
                List<Province> leftCastles = new ArrayList<>();
                List<Province> rightCastles = new ArrayList<>();

                leftCastles.add(new Province('$', -1, -1));
                rightCastles.add(new Province('$', m, n));

                for (Province province : provinces) {
                    if (province.boundRight < favourite.boundLeft && province.boundLeft < favourite.boundLeft) {
                        leftCastles.add(province);
                    }
                    if (province.boundLeft > favourite.boundRight && province.boundRight > favourite.boundRight) {
                        rightCastles.add(province);
                    }
                }

                int maxArea = 0;
                Bounds maxBounds = new Bounds(favourite.boundLeft,
                        favourite.boundRight,
                        favourite.boundDown,
                        favourite.boundUp);

                // Forall left nodes forall right nodes find up-down limits and calculate area
                for (Province leftBound : leftCastles) {
                    if (leftBound.boundRight + 1 < horizontalLimits.boundLeft) {
                        continue;
                    }
                    for (Province rightBound : rightCastles) {
                        if (rightBound.boundLeft - 1 > horizontalLimits.boundRight) {
                            continue;
                        }

                        Bounds favouriteLimits = new Bounds(leftBound.boundRight + 1,
                                rightBound.boundLeft - 1,
                                favourite.boundDown,
                                favourite.boundUp);

                        favouriteLimits = expandVertically(favouriteLimits, provinces, n);

                        int curArea = (favouriteLimits.boundDown - favouriteLimits.boundUp + 1) *
                                (favouriteLimits.boundRight - favouriteLimits.boundLeft + 1);
                        if (curArea > maxArea) {
                            maxArea = curArea;
                            maxBounds = new Bounds(favouriteLimits);
                        }
                    }
                }

                copyBounds(maxBounds, favourite);

                //Expand all bounds as far as possible
                for (Province province : provinces) {
                    Bounds newBounds = expandVertically(province, provinces, n);
                    copyBounds(newBounds, province);
                }
                for (Province province : provinces) {
                    Bounds newBounds = expandHorizontally(province, provinces, m);
                    copyBounds(newBounds, province);
                }

                // Fill the calculated area with the right letter
                for (Province province : provinces) {
                    for (int i = province.boundUp; i <= province.boundDown; i++) {
                        Arrays.fill(textInput[i],
                                province.boundLeft,
                                province.boundRight + 1,
                                Character.toLowerCase(province.idChar));
                    }
                }

                //Restore original castle-marker letters
                for (Province province : provinces) {
                    textInput[province.y][province.x] = province.idChar;
                }

                // Print the text array out
                for (char[] i : textInput) {
                    for (char j : i) {
                        System.out.write(j);
                    }
                    System.out.write('\n');
                }
            } catch (NoSuchElementException e) {
                System.err.println("Bad input format: " + e.getMessage());
            } catch (IllegalStateException e) {
                System.err.println("IO error: " + e.getMessage());
            }
        } catch (IOException e) {
            System.err.println("Error reading input: " + e.getMessage());
        } catch (OutOfMemoryError e) {
            System.err.println("Not enough memory: " + e.getMessage());
        }
    }

    static Bounds expandHorizontally(Bounds origin, List<Province> field, int width) {
        Bounds ans = new Bounds(0, width - 1, origin.boundDown, origin.boundUp);
        for (Bounds bounds : field) {
            if (boundIntersects(bounds.boundUp, bounds.boundDown, origin.boundUp, origin.boundDown)) {
                if (bounds.boundRight < origin.boundLeft) {
                    ans.boundLeft = Math.max(ans.boundLeft, bounds.boundRight + 1);
                }
                if (bounds.boundLeft > origin.boundRight) {
                    ans.boundRight = Math.min(ans.boundRight, bounds.boundLeft - 1);
                }
            }
        }
        return ans;
    }

    static Bounds expandVertically(Bounds origin, List<Province> field, int height) {
        Bounds ans = new Bounds(origin.boundLeft, origin.boundRight, height - 1, 0);
        for (Bounds bounds : field) {
            if (boundIntersects(origin.boundLeft, origin.boundRight, bounds.boundLeft, bounds.boundRight)) {
                if (bounds.boundDown < origin.boundUp) {
                    ans.boundUp = Math.max(ans.boundUp, bounds.boundDown + 1);
                }
                if (bounds.boundUp > origin.boundDown) {
                    ans.boundDown = Math.min(ans.boundDown, bounds.boundUp - 1);
                }
            }
        }
        return ans;
    }

    static void copyBounds(Bounds origin, Bounds target) {
        target.boundDown = origin.boundDown;
        target.boundLeft = origin.boundLeft;
        target.boundUp = origin.boundUp;
        target.boundRight = origin.boundRight;
    }

    static boolean boundIntersects(int lowerA, int upperA, int lowerB, int upperB) {
        return lowerA < lowerB && upperA >= lowerB ||
                upperA > upperB && lowerA <= upperB ||
                lowerA >= lowerB && upperA <= upperB;
    }
}

class Bounds {
    public int boundLeft;
    public int boundRight;
    public int boundUp;
    public int boundDown;

    public Bounds(int left, int right, int down, int up) {
        boundLeft = left;
        boundRight = right;
        boundDown = down;
        boundUp = up;
    }

    public Bounds(Bounds origin) {
        this(origin.boundLeft, origin.boundRight, origin.boundDown, origin.boundUp);
    }
}

class Province extends Bounds {
    public final char idChar;
    public final int x, y;

    public Province(char id, int x, int y) {
        super(x, x, y, y);
        this.x = x;
        this.y = y;
        idChar = id;
    }
}