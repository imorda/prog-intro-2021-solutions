package game;

import java.util.*;
import java.util.function.Function;

public class MainHexTournament {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        final Map<ParticipantData, Integer> tournamentTable = new LinkedHashMap<>();

        List<ParticipantData> participants = new ArrayList<>();

        try {
            System.out.println("Enter total participants count:");
            int n = in.nextInt();

            System.out.println("Available player types: 1 - Human, 2 - Random, 3 - Sequential");
            System.out.println("Enter participant's data (format: '%NAME% %PLAYER_TYPE%'). 1 Player per line:");
            for (int i = 0; i < n; i++) {
                String name = in.next();
                switch (in.nextInt()) {
                    case 1 -> participants.add(new ParticipantData(name, HumanPlayer::new));
                    case 2 -> participants.add(new ParticipantData(name, RandomPlayer::new));
                    case 3 -> participants.add(new ParticipantData(name, SequentialPlayer::new));
                    default -> System.out.println("You have entered wrong player type");
                }
            }
        } catch (InputMismatchException e) {
            System.err.println("Incorrect input format: " + e.getMessage());
            return;
        } catch (NoSuchElementException | IllegalStateException e) {
            System.err.println("Input error: " + e.getMessage());
            return;
        }


        for (int i = 0; i < participants.size(); i++) {
            for (int j = 0; j < participants.size(); j++) {
                if (j != i) {
                    String firstPlayerColor = HexBoard11x11.BRD_ORIENTATION_TO_STRING
                            .get(HexBoard11x11.CELL_TO_BRD_ORIENTATION.get(Cell.BLUE));
                    String secondPlayerColor = HexBoard11x11.BRD_ORIENTATION_TO_STRING
                            .get(HexBoard11x11.CELL_TO_BRD_ORIENTATION.get(Cell.RED));
                    String neutralColor = HexBoard11x11.getNeutralColor();
                    System.out.format("%s%s%s is now playing with %s%s%s!%s",
                            firstPlayerColor, participants.get(i), neutralColor,
                            secondPlayerColor, participants.get(j), neutralColor,
                            System.lineSeparator());
                    final int result = new TwoPlayerGame(
                            new HexBoard11x11(Cell.BLUE),
                            participants.get(i).constructor.apply(in),
                            participants.get(j).constructor.apply(in)
                    ).play(false);

                    switch (result) {
                        case 1 -> {
                            System.out.format("%s won! +3 points%s", participants.get(i), System.lineSeparator());
                            tournamentTable.put(participants.get(i),
                                    tournamentTable.getOrDefault(participants.get(i), 0) + 3);
                        }
                        case 2 -> {
                            System.out.format("%s won! +3 points%s", participants.get(j), System.lineSeparator());
                            tournamentTable.put(participants.get(j),
                                    tournamentTable.getOrDefault(participants.get(j), 0) + 3);
                        }
                        case 0 -> {
                            System.out.println("Draw. +1 point for both");
                            tournamentTable.put(participants.get(i),
                                    tournamentTable.getOrDefault(participants.get(i), 0) + 1);
                            tournamentTable.put(participants.get(j),
                                    tournamentTable.getOrDefault(participants.get(j), 0) + 1);
                        }
                        default -> throw new AssertionError("Unknown result " + result);
                    }
                }
            }
        }

        System.out.println();
        System.out.println();
        System.out.println("Tournament results:");
        for (var i : tournamentTable.entrySet()) {
            System.out.format("%s:\t%d%s", i.getKey(), i.getValue(), System.lineSeparator());
        }
    }

    record ParticipantData(String name, Function<Scanner, Player> constructor) {
        @Override
        public String toString() {
            return name + "(" + hashCode() + ")";
        }
    }
}
