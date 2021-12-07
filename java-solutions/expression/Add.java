package expression;

import java.math.BigInteger;

public final class Add extends AssociativeOperation {
    public final static String operationSym = "+";

    public Add(PriorityExpression left, PriorityExpression right) {
        super(left, right, 1, 0);
    }

    @Override
    public String getBinaryOperationSymbol() {
        return operationSym;
    }

    @Override
    public BigInteger evaluate(BigInteger x) {
        return left.evaluate(x).add(right.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return left.evaluate(x, y, z) + right.evaluate(x, y, z);
    }

    @Override
    public int evaluate(int x) {
        return left.evaluate(x) + right.evaluate(x);
    }
}