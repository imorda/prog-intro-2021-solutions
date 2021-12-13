package expression;

import java.math.BigInteger;

public final class TZero extends UnaryOperation {
    public final static String operationSym = "t0";

    public TZero(PriorityExpression exp) {
        super(exp, -1, 0);
    }

    @Override
    public String getUnaryOperationSymbol() {
        return operationSym;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return Integer.numberOfTrailingZeros(exp.evaluate(x, y, z));
    }

    @Override
    public int evaluate(int x) {
        return Integer.numberOfTrailingZeros(exp.evaluate(x));
    }

    @Override
    public BigInteger evaluate(BigInteger x) {
        throw new UnsupportedOperationException("Trailing Zero does not support BigIntegers");
    }
}