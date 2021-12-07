package game;

import java.util.List;

public class HexBoardMNK extends TicTacToeBoardMNK {
    private static final List<IntPair> ADJACENT_CELL_POS = List.of(
            new IntPair(1, 0),
            new IntPair(0, 1),
            new IntPair(1, -1)
    );

    public HexBoardMNK(int m, int n, int k, Cell firstMove) {
        super(m, n, k, firstMove);
    }

    @Override
    protected boolean checkWin(Move lastMove) {
        for (IntPair i : ADJACENT_CELL_POS) {
            if (calculateRowLength(lastMove, i.getFirst(), i.getSecond())
                    + calculateRowLength(lastMove, -i.getFirst(), -i.getSecond()) + 1 >= winRowLen) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String serializeHumanReadableRepresentation() {
        final int cellWidth = String.valueOf(Math.max(getBoardColumnCount(), getBoardRowCount())).length();

        final StringBuilder sb = new StringBuilder();

        sbAppendNTimes(sb, ' ', cellWidth);
        for (int i = 1; i <= getBoardColumnCount(); i++) {
            String curNumber = String.valueOf(i);
            sbAppendNTimes(sb, ' ', cellWidth - curNumber.length());
            sb.append(curNumber);
            sb.append(' ');
        }
        sb.append(System.lineSeparator());

        for (int r = 0; r < getBoardRowCount(); r++) {
            String rowNumber = String.valueOf(r + 1);
            sbAppendNTimes(sb, ' ', cellWidth * r + cellWidth - rowNumber.length());
            sb.append(rowNumber).append(' ');

            for (int c = 0; c < getBoardColumnCount(); c++) {
                Cell cell = field[r][c];
                sbAppendNTimes(sb, ' ', cellWidth - 1);

                sb.append(CELL_TO_STRING.get(cell));

                sb.append(' ');
            }
            sb.append(System.lineSeparator());
        }
        sb.setLength(sb.length() - System.lineSeparator().length());
        return sb.toString();
    }
}
