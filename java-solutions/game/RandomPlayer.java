package game;

import java.util.Random;
import java.util.Scanner;

public class RandomPlayer implements Player {
    private final Random random = new Random();

    public RandomPlayer() {

    }

    public RandomPlayer(Scanner scanner) {
        this();
    }

    @Override
    public Move makeMove(Position position) {
        while (true) {
            final Move move = new Move(
                    random.nextInt(position.getBoardRowCount()),
                    random.nextInt(position.getBoardColumnCount()),
                    position.getTurn()
            );
            if (position.isValid(move)) {
                return move;
            }
        }
    }
}
