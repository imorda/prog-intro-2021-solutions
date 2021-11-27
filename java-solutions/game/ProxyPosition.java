package game;

public class ProxyPosition implements Position {
    private final Position origin;

    public ProxyPosition(final Position origin) {
        this.origin = origin;
    }

    @Override
    public Cell getTurn() {
        return origin.getTurn();
    }

    @Override
    public boolean isValid(Move move) {
        return origin.isValid(move);
    }

    @Override
    public Cell getCell(int row, int column) {
        return origin.getCell(row, column);
    }

    @Override
    public int getBoardRowCount() {
        return origin.getBoardRowCount();
    }

    @Override
    public int getBoardColumnCount() {
        return origin.getBoardColumnCount();
    }

    @Override
    public int getWinRowLen() {
        return origin.getWinRowLen();
    }

    @Override
    public String toString() {
        return origin.toString();
    }
}
