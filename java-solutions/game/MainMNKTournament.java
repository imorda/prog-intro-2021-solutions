package game;

import java.util.*;

public class MainMNKTournament extends AbstractTournament {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        MNKParams mnk = aquireMNK(in);
        if (mnk == null) {
            return;
        }

        List<ParticipantData> participants = acquireParticipantsList(in);
        if (participants == null) {
            return;
        }

        final Map<ParticipantData, Integer> tournamentTable = new LinkedHashMap<>();


        for (int i = 0; i < participants.size(); i++) {
            for (int j = 0; j < participants.size(); j++) {
                if (j != i) {
                    System.out.format("%s[%s] is now playing with %s[%s]!%s",
                            participants.get(i), Cell.X,
                            participants.get(j), Cell.O,
                            System.lineSeparator());
                    final int result = new TwoPlayerGame(
                            new HexBoardMNK(mnk.m(), mnk.n(), mnk.k(), Cell.X),
                            participants.get(i).constructor().apply(in),
                            participants.get(j).constructor().apply(in)
                    ).play(false);

                    printResults(result, tournamentTable, participants, i, j);
                }
            }
        }

        printTournamentResults(tournamentTable);
    }

    protected static MNKParams aquireMNK(Scanner in) {
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
                return null;
            }
        }
        return new MNKParams(m, n, k);
    }

    protected record MNKParams(int m, int n, int k) {
    }
}
