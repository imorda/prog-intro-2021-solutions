package game;

import java.util.*;
import java.util.function.Function;

public abstract class AbstractTournament {
    protected static List<ParticipantData> acquireParticipantsList(Scanner in) {
        while (true) {
            List<ParticipantData> participants = new ArrayList<>();

            try {
                System.out.println("Enter total participants count:");
                int playerCount = in.nextInt();

                System.out.println("Available player types: 1 - Human, 2 - Random, 3 - Sequential");
                System.out.println("Enter participant's data (format: '%NAME% %PLAYER_TYPE%'). 1 Player per line:");
                for (int i = 0; i < playerCount; i++) {
                    String name = in.next();
                    switch (in.nextInt()) {
                        case 1 -> participants.add(new ParticipantData(name, HumanPlayer::new));
                        case 2 -> participants.add(new ParticipantData(name, RandomPlayer::new));
                        case 3 -> participants.add(new ParticipantData(name, SequentialPlayer::new));
                        default -> {
                            System.out.println("You have entered wrong player type, try again");
                            i--;
                        }
                    }
                }
                return participants;
            } catch (InputMismatchException e) {
                System.err.println("Incorrect input format: " + e.getMessage() + ". Players list reset.");
                in.nextLine();
            } catch (NoSuchElementException | IllegalStateException e) {
                System.err.println("Input error: " + e.getMessage());
                return null;
            }
        }
    }

    protected static void printResults(int result,
                                       Map<ParticipantData, Integer> tournamentTable,
                                       List<ParticipantData> participants,
                                       int participant1,
                                       int participant2) {
        switch (result) {
            case 1 -> {
                System.out.format("%s won! +3 points%s", participants.get(participant1), System.lineSeparator());
                tournamentTable.put(participants.get(participant1),
                        tournamentTable.getOrDefault(participants.get(participant1), 0) + 3);
            }
            case 2 -> {
                System.out.format("%s won! +3 points%s", participants.get(participant2), System.lineSeparator());
                tournamentTable.put(participants.get(participant2),
                        tournamentTable.getOrDefault(participants.get(participant2), 0) + 3);
            }
            case 0 -> {
                System.out.println("Draw. +1 point for both");
                tournamentTable.put(participants.get(participant1),
                        tournamentTable.getOrDefault(participants.get(participant1), 0) + 1);
                tournamentTable.put(participants.get(participant2),
                        tournamentTable.getOrDefault(participants.get(participant2), 0) + 1);
            }
            default -> throw new AssertionError("Unknown result " + result);
        }
    }

    protected static void printTournamentResults(Map<ParticipantData, Integer> tournamentTable) {
        System.out.println();
        System.out.println();
        System.out.println("Tournament results:");
        for (var i : tournamentTable.entrySet()) {
            System.out.format("%s:\t%d%s", i.getKey(), i.getValue(), System.lineSeparator());
        }
    }

    protected record ParticipantData(String name, Function<Scanner, Player> constructor) {
        @Override
        public String toString() {
            return name + "(" + hashCode() + ")";
        }
    }
}
