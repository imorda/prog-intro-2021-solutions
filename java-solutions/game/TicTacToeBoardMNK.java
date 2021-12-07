package game;

import java.util.Map;

public class TicTacToeBoardMNK extends Abstract2DBoard {
    protected static final Map<Cell, String> CELL_TO_STRING = Map.of(
            Cell.EMPTY, "•",
            Cell.X, "X",
            Cell.O, "0"
    );
    private final static char VERTICAL_SEPARATOR = '│';
    private final static char VERTICAL_BOLD_SEPARATOR = '║';
    private final static char HORIZONTAL_BOLD_SEPARATOR = '═';
    private final static char H_CROSS_BOLD_SEPARATOR = '╪';
    private final static char CROSS_BOLD_SEPARATOR = '╬';
    private final static char BOLD_T_RIGHT_SEPARATOR = '╡';
    protected final int winRowLen;

    public TicTacToeBoardMNK(int brdRowCount, int brdColumnCount, int winRowLen, Cell firstMove) {
        super(brdRowCount, brdColumnCount, firstMove);
        this.winRowLen = winRowLen;
    }

    @Override
    public int getWinRowLen() {
        return winRowLen;
    }

    @Override
    protected boolean checkWin(Move lastMove) {
        return calculateRowLength(lastMove, -1, -1) + calculateRowLength(lastMove, 1, 1) + 1 >= winRowLen ||
                calculateRowLength(lastMove, -1, 0) + calculateRowLength(lastMove, 1, 0) + 1 >= winRowLen ||
                calculateRowLength(lastMove, 0, -1) + calculateRowLength(lastMove, 0, 1) + 1 >= winRowLen;
    }

    protected int calculateRowLength(Move lastMove, int dr, int dc) {
        int r = lastMove.getRow() + dr;
        int c = lastMove.getCol() + dc;

        int ans = 0;
        while (0 <= r && r < getBoardRowCount() && 0 <= c && c < getBoardColumnCount()) {
            if (getCell(r, c) == turn) {
                ans++;
                r += dr;
                c += dc;
            } else {
                break;
            }
        }

        return ans;
    }

    @Override
    public String serializeHumanReadableRepresentation() {
        final int cellWidth = String.valueOf(Math.max(getBoardColumnCount(), getBoardRowCount())).length();

        final StringBuilder sb = new StringBuilder();

        sbAppendNTimes(sb, ' ', cellWidth);
        sb.append(VERTICAL_BOLD_SEPARATOR);
        for (int i = 1; i <= getBoardColumnCount(); i++) {
            String curNumber = String.valueOf(i);
            sbAppendNTimes(sb, ' ', cellWidth - curNumber.length());
            sb.append(curNumber);

            sb.append(VERTICAL_SEPARATOR);
        }
        sb.append(System.lineSeparator());

        sbAppendNTimes(sb, HORIZONTAL_BOLD_SEPARATOR, cellWidth);
        sb.append(CROSS_BOLD_SEPARATOR);
        for (int r = 0; r < getBoardColumnCount(); r++) {
            sbAppendNTimes(sb, HORIZONTAL_BOLD_SEPARATOR, cellWidth);
            if (r < getBoardColumnCount() - 1) {
                sb.append(H_CROSS_BOLD_SEPARATOR);
            } else {
                sb.append(BOLD_T_RIGHT_SEPARATOR);
            }
        }
        sb.append(System.lineSeparator());

        for (int r = 0; r < getBoardRowCount(); r++) {
            String rowNumber = String.valueOf(r + 1);
            sbAppendNTimes(sb, ' ', cellWidth - rowNumber.length());
            sb.append(rowNumber).append(VERTICAL_BOLD_SEPARATOR);

            for (int c = 0; c < getBoardColumnCount(); c++) {
                Cell cell = field[r][c];
                sbAppendNTimes(sb, ' ', cellWidth - 1);
                sb.append(CELL_TO_STRING.get(cell));

                sb.append(VERTICAL_SEPARATOR);
            }
            sb.append(System.lineSeparator());
        }
        sb.setLength(sb.length() - System.lineSeparator().length());
        return sb.toString();
    }
}
