package expression;

import java.math.BigInteger;

public final class LZero extends UnaryOperation {
    public final static String operationSym = "l0";

    public LZero(PriorityExpression exp) {
        super(exp, -1, 0);
    }

    private static int calculateLeadingZeros(int value) {
        if (value == 0) {
            return 32;
        }

        int ans = 0;
        while ((value & (1 << 31)) == 0) {
            value <<= 1;
            ans++;
        }

        return ans;
    }

    @Override
    public String getUnaryOperationSymbol() {
        return operationSym;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return calculateLeadingZeros(exp.evaluate(x, y, z));
    }

    @Override
    public int evaluate(int x) {
        return calculateLeadingZeros(exp.evaluate(x));
    }

    @Override
    public BigInteger evaluate(BigInteger x) {
        throw new UnsupportedOperationException("Leading Zero does not support BigIntegers");
    }
}