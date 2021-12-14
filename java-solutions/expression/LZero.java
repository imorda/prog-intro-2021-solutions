package expression;

import java.math.BigInteger;

public final class LZero extends UnaryOperation {
    public LZero(PriorityExpression exp) {
        super(exp);
    }

    @Override
    protected int getPriority() {
        return -1;
    }

    @Override
    protected int getLocalPriority() {
        return 0;
    }

    @Override
    public String getUnaryOperationSymbol() {
        return "l0";
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