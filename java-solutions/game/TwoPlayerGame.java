package game;

public class TwoPlayerGame {
    private final Board board;
    private final Player player1;
    private final Player player2;

    public TwoPlayerGame(Board board, Player player1, Player player2) {
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
    }

    public static void printResults(int resultId) {
        switch (resultId) {
            case 1 -> System.out.println("First player won");
            case 2 -> System.out.println("Second player won");
            case 0 -> System.out.println("Draw");
            default -> throw new AssertionError("Unknown result " + resultId);
        }
    }

    public int play(boolean log) {
        while (true) {
            final int result1 = makeMove(player1, 1, log);
            if (result1 != -1) {
                return result1;
            }
            final int result2 = makeMove(player2, 2, log);
            if (result2 != -1) {
                return result2;
            }
        }
    }

    private int makeMove(Player player, int no, boolean log) {
        try {
            final Move move = player.makeMove(new ProxyPosition(board.getPosition()));
            final GameResult result = board.makeMove(move);
            if (log) {
                System.out.println();
                System.out.println("Player: " + no);
                System.out.println(move);
                System.out.println(board);
                System.out.println("Result: " + result);
            }
            return switch (result) {
                case WIN -> no;
                case LOSE -> 3 - no;
                case DRAW -> 0;
                case UNKNOWN -> -1;
            };
        } catch (AssertionError e) {
            System.out.format("Player %d disqualified%s", no, System.lineSeparator());
            return 3 - no; //LOSE
        }
    }
}
