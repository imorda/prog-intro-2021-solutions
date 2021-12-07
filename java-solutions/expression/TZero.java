package expression;

import java.math.BigInteger;

public final class TZero extends UnaryOperation {
    public final static String operationSym = "t0";

    public TZero(PriorityExpression exp) {
        super(exp, -1, 0);
    }

    private static int calculateTrailingZeros(int value) {
        if (value == 0) {
            return 32;
        }

        int ans = 0;
        while ((value & 1) == 0) {
            value >>= 1;
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
        return calculateTrailingZeros(exp.evaluate(x, y, z));
    }

    @Override
    public int evaluate(int x) {
        return calculateTrailingZeros(exp.evaluate(x));
    }

    @Override
    public BigInteger evaluate(BigInteger x) {
        throw new UnsupportedOperationException("Trailing Zero does not support BigIntegers");
    }
}