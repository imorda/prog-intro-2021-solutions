package game;

public interface Position {
    Cell getTurn();

    boolean isValid(Move move);

    Cell getCell(int row, int column);

    int getBoardRowCount();

    int getBoardColumnCount();

    int getWinRowLen();

    String serializeHumanReadableRepresentation();
}
