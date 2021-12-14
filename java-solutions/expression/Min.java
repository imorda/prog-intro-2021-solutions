package expression;

import java.math.BigInteger;

public final class Min extends AssociativeOperation {
    public final static String operationSym = "min";

    public Min(PriorityExpression left, PriorityExpression right) {
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
        return operationSym;
    }

    @Override
    public BigInteger evaluate(BigInteger x) {
        throw new UnsupportedOperationException("BigIntegers are unsupported for min operation");
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return Math.min(left.evaluate(x, y, z), right.evaluate(x, y, z));
    }

    @Override
    public int evaluate(int x) {
        return Math.min(left.evaluate(x), right.evaluate(x));
    }
}