package game;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

public class HumanPlayer implements Player {
    private final Scanner in;

    public HumanPlayer(Scanner in) {
        this.in = Objects.requireNonNull(in);
    }

    @Override
    public Move makeMove(Position position) {
        System.out.println();
        System.out.println("Current position");
        System.out.println(position);

        while (true) {
            System.out.println("Enter your move for " + position.getTurn());
            try {
                Move enteredMove = new Move(in.nextInt() - 1, in.nextInt() - 1, position.getTurn());
                if (!position.isValid(enteredMove)) {
                    throw new AssertionError("Move is invalid");
                }
                return enteredMove;
            } catch (AssertionError e) {
                System.out.println("Sorry, you've entered invalid move. Try again. (" + e.getMessage() + ")");
            } catch (InputMismatchException e) {
                System.out.println("Wrong input format. You have to input two numbers: row and column. (" +
                        e.getMessage() + ")");
                in.nextLine();
            } catch (IllegalStateException | NoSuchElementException e) {
                System.err.println("Input error: " + e.getMessage());
            }
        }
    }
}
