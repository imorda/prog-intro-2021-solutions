package expression;

import java.math.BigInteger;

public class Divide extends NonAssociativeOperation {
    public final static String operationSym = "/";

    public Divide(PriorityExpression left, PriorityExpression right) {
        super(left, right, 0, -1);
    }

    @Override
    public String getBinaryOperationSymbol() {
        return operationSym;
    }

    @Override
    public BigInteger evaluate(BigInteger x) {
        return left.evaluate(x).divide(right.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return left.evaluate(x, y, z) / right.evaluate(x, y, z);
    }

    @Override
    public int evaluate(int x) {
        return left.evaluate(x) / right.evaluate(x);
    }
}