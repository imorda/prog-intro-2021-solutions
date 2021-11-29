package game;

import java.util.Scanner;

public class MainHex {
    public static void main(String[] args) {
        final int result = new TwoPlayerGame(
                new HexBoard11x11(Cell.BLUE),
                new RandomPlayer(),
//                new SequentialPlayer()
                new HumanPlayer(new Scanner(System.in))
        ).play(true);

        TwoPlayerGame.printResults(result);
    }
}
