package expression;

import java.math.BigInteger;

public final class Max extends AssociativeOperation {
    public Max(PriorityExpression left, PriorityExpression right) {
        super(left, right);
    }

    @Override
    protected int getPriority() {
        return 2;
    }

    @Override
    protected int getLocalPriority() {
        return 0;
    }

    @Override
    public String getBinaryOperationSymbol() {
        return "max";
    }

    @Override
    public BigInteger evaluate(BigInteger x) {
        throw new UnsupportedOperationException("BigIntegers are unsupported for max operation");
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return Math.max(left.evaluate(x, y, z), right.evaluate(x, y, z));
    }

    @Override
    public int evaluate(int x) {
        return Math.max(left.evaluate(x), right.evaluate(x));
    }
}