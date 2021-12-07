package game;

import java.util.Arrays;

public abstract class Abstract2DBoard implements Board, Position {
    protected final Cell[][] field;
    protected Cell turn;
    protected int availableTurns;

    public Abstract2DBoard(int brdRowCount, int brdColumnCount, Cell firstMove) {
        field = new Cell[brdRowCount][brdColumnCount];
        for (Cell[] row : field) {
            Arrays.fill(row, Cell.EMPTY);
        }
        turn = firstMove;
        availableTurns = brdRowCount * brdColumnCount;
    }

    protected static void sbAppendNTimes(StringBuilder sb, String data, int repeat) {
        sb.append(String.valueOf(data).repeat(Math.max(0, repeat)));
    }

    protected static void sbAppendNTimes(StringBuilder sb, char data, int repeat) {
        sbAppendNTimes(sb, String.valueOf(data), repeat);
    }

    @Override
    public int getBoardRowCount() {
        return field.length;
    }

    @Override
    public int getBoardColumnCount() {
        return field[0].length;
    }

    @Override
    public Cell getTurn() {
        return turn;
    }

    @Override
    public Position getPosition() {
        return this;
    }

    @Override
    public GameResult makeMove(Move move) {
        if (!isValid(move)) {
            return GameResult.LOSE;
        }

        field[move.getRow()][move.getCol()] = move.getValue();
        availableTurns--;

        if (checkWin(move)) {
            return GameResult.WIN;
        }

        if (checkDraw()) {
            return GameResult.DRAW;
        }

        turn = turn == Cell.X ? Cell.O : Cell.X;
        return GameResult.UNKNOWN;
    }

    protected abstract boolean checkWin(Move lastMove);

    protected boolean checkDraw() {
        return availableTurns <= 0;
    }

    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < getBoardRowCount()
                && 0 <= move.getCol() && move.getCol() < getBoardColumnCount()
                && field[move.getRow()][move.getCol()] == Cell.EMPTY
                && turn == move.getValue();
    }

    @Override
    public Cell getCell(int row, int column) {
        return field[row][column];
    }
}
