package expression;

import java.math.BigInteger;

public final class Max extends AssociativeOperation {
    public final static String operationSym = "max";

    public Max(PriorityExpression left, PriorityExpression right) {
        super(left, right, 2, 0);
    }

    @Override
    public String getBinaryOperationSymbol() {
        return operationSym;
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