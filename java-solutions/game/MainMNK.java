package game;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MainMNK {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int m;
        int n;
        int k;
        while (true) {
            try {
                System.out.println("Enter 'M' (board height):");
                m = in.nextInt();
                System.out.println("Enter 'N' (board width):");
                n = in.nextInt();
                System.out.println("Enter 'K' (winning row length):");
                k = in.nextInt();
                if (m <= 0 || n <= 0 || k <= 0) {
                    System.out.println("You've entered incorrect game parameters. Try again");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Incorrect input format. Enter a NUMBER");
                in.nextLine();
            } catch (NoSuchElementException | IllegalStateException e) {
                System.err.println("Input error: " + e.getMessage());
            }
        }

        final int result = new TwoPlayerGame(
                new TicTacToeBoardMNK(m, n, k),
                new RandomPlayer(),
//                new SequentialPlayer()
                new HumanPlayer(in)
        ).play(false);

        TwoPlayerGame.printResults(result);
    }
}
