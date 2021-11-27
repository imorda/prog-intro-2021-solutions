package game;

import java.util.Scanner;

public class SequentialPlayer implements Player {

    public SequentialPlayer() {
    }

    public SequentialPlayer(Scanner scanner) {
        this();
    }

    @Override
    public Move makeMove(Position position) {
        for (int r = 0; r < position.getBoardRowCount(); r++) {
            for (int c = 0; c < position.getBoardColumnCount(); c++) {
                final Move move = new Move(r, c, position.getTurn());
                if (position.isValid(move)) {
                    return move;
                }
            }
        }
        throw new AssertionError("No valid moves");
    }
}
