package expression;

import java.math.BigInteger;

public final class LZero extends UnaryOperation {
    public final static String operationSym = "l0";

    public LZero(PriorityExpression exp) {
        super(exp, -1, 0);
    }

    @Override
    public String getUnaryOperationSymbol() {
        return operationSym;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return Integer.numberOfLeadingZeros(exp.evaluate(x, y, z));
    }

    @Override
    public int evaluate(int x) {
        return Integer.numberOfLeadingZeros(exp.evaluate(x));
    }

    @Override
    public BigInteger evaluate(BigInteger x) {
        throw new UnsupportedOperationException("Leading Zero does not support BigIntegers");
    }
}