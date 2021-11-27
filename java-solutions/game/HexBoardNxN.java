package game;

import java.util.List;
import java.util.Map;

public class HexBoardNxN extends Abstract2DBoard {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_BLUE = "\u001B[34m";

    private static final Map<ColorOrientation, String> BRD_ORIENTATION_TO_STRING = Map.of(
            ColorOrientation.UNKNOWN, ANSI_RESET,
            ColorOrientation.X, ANSI_RED,
            ColorOrientation.Y, ANSI_BLUE
    );

    private static final Map<Cell, ColorOrientation> CELL_TO_BRD_ORIENTATION = Map.of(
            Cell.EMPTY, ColorOrientation.UNKNOWN,
            Cell.RED, ColorOrientation.X,
            Cell.BLUE, ColorOrientation.Y
    );

    private static final List<IntPair> ADJACENT_CELL_POS = List.of(
            new IntPair(1, 0),
            new IntPair(-1, 0),
            new IntPair(0, 1),
            new IntPair(0, -1),
            new IntPair(1, -1),
            new IntPair(-1, 1)
    );

    public HexBoardNxN(int sideSize) {
        super(sideSize, sideSize);
    }

    @Override
    public int getWinRowLen() {
        throw new UnsupportedOperationException("There is no win row length in game 'Hex'");
    }

    @Override
    protected boolean checkWin(Move lastMove) {
        IntPair position = lastMove.getPosition();
        return dfsCheckSideConnection(new boolean[field.length][field.length],
                position, position, position);
    }

    private boolean dfsCheckSideConnection(boolean[][] markedCells,
                                           IntPair minBounds, IntPair maxBounds, IntPair pos) {
        if (minBounds.getFirst() < 0 || minBounds.getSecond() < 0
                || maxBounds.getFirst() >= getBoardRowCount() || maxBounds.getSecond() >= getBoardRowCount()) {
            return false;
        }


        if (field[pos.getFirst()][pos.getSecond()] != turn) {
            return false;
        }

        if (markedCells[pos.getFirst()][pos.getSecond()]) {
            return false;
        }

        switch (CELL_TO_BRD_ORIENTATION.get(turn)) {
            case X:
                if (minBounds.getFirst() == 0 && maxBounds.getFirst() == getBoardRowCount() - 1) {
                    return true;
                }
                break;
            case Y:
                if (minBounds.getSecond() == 0 && maxBounds.getSecond() == getBoardRowCount() - 1) {
                    return true;
                }
                break;
            default:
                throw new AssertionError("Cell color orientation undefined");
        }

        markedCells[pos.getFirst()][pos.getSecond()] = true;

        for (IntPair i : ADJACENT_CELL_POS) {
            IntPair newPos = pos.add(i);

            if (dfsCheckSideConnection(markedCells,
                    new IntPair(Math.min(minBounds.getFirst(), newPos.getFirst()),
                            Math.min(minBounds.getSecond(), newPos.getSecond())),
                    new IntPair(Math.max(maxBounds.getFirst(), newPos.getFirst()),
                            Math.max(maxBounds.getSecond(), newPos.getSecond())),
                    newPos)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        final int cellWidth = String.valueOf(Math.max(getBoardColumnCount(), getBoardRowCount())).length();

        final StringBuilder sb = new StringBuilder();

        sbAppendNTimes(sb, ' ', cellWidth);
        for (int i = 1; i <= getBoardColumnCount(); i++) {
            String curNumber = String.valueOf(i);
            sb.append(BRD_ORIENTATION_TO_STRING.get(ColorOrientation.X));
            sbAppendNTimes(sb, ' ', cellWidth - curNumber.length());
            sb.append(curNumber);
            sb.append(' ');
        }
        sb.append(ANSI_RESET);
        sb.append(System.lineSeparator());

        for (int r = 0; r < getBoardRowCount(); r++) {
            String rowNumber = String.valueOf(r + 1);
            sbAppendNTimes(sb, ' ', cellWidth * r + cellWidth - rowNumber.length());
            sb.append(BRD_ORIENTATION_TO_STRING.get(ColorOrientation.Y))
                    .append(rowNumber)
                    .append(ANSI_RESET)
                    .append(' ');

            for (int c = 0; c < getBoardColumnCount(); c++) {
                Cell cell = field[r][c];
                sbAppendNTimes(sb, ' ', cellWidth - 1);

                if (CELL_TO_BRD_ORIENTATION.get(cell) == ColorOrientation.UNKNOWN) {
                    sb.append("•");
                } else {
                    sb.append(BRD_ORIENTATION_TO_STRING.get(CELL_TO_BRD_ORIENTATION.get(cell)))
                            .append("■").append(ANSI_RESET);
                }

                sb.append(' ');
            }
            sb.append(System.lineSeparator());
        }
        sb.setLength(sb.length() - System.lineSeparator().length());
        return sb.toString();
    }
}
