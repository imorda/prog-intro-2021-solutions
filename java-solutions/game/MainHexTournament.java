package game;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MainHexTournament extends AbstractTournament {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        List<MainMNKTournament.ParticipantData> participants = acquireParticipantsList(in);
        if (participants == null) {
            return;
        }


        final Map<ParticipantData, Integer> tournamentTable = new LinkedHashMap<>();

        for (int i = 0; i < participants.size(); i++) {
            for (int j = 0; j < participants.size(); j++) {
                if (j != i) {
                    String firstPlayerColor = HexBoard11x11.BRD_ORIENTATION_TO_STRING
                            .get(HexBoard11x11.CELL_TO_BRD_ORIENTATION.get(Cell.O));
                    String secondPlayerColor = HexBoard11x11.BRD_ORIENTATION_TO_STRING
                            .get(HexBoard11x11.CELL_TO_BRD_ORIENTATION.get(Cell.X));
                    String neutralColor = HexBoard11x11.getNeutralColor();
                    System.out.format("%s%s%s is now playing with %s%s%s!%s",
                            firstPlayerColor, participants.get(i), neutralColor,
                            secondPlayerColor, participants.get(j), neutralColor,
                            System.lineSeparator());
                    final int result = new TwoPlayerGame(
                            new HexBoard11x11(Cell.O),
                            participants.get(i).constructor().apply(in),
                            participants.get(j).constructor().apply(in)
                    ).play(false);

                    printResults(result, tournamentTable, participants, i, j);
                }
            }
        }

        printTournamentResults(tournamentTable);
    }
}
