package expression;

import java.math.BigInteger;

public final class UnaryMinus extends UnaryOperation {
    public final static String operationSym = "-";

    public UnaryMinus(PriorityExpression exp) {
        super(exp, -1, 0);
    }

    @Override
    public String getUnaryOperationSymbol() {
        return operationSym;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return -exp.evaluate(x, y, z);
    }

    @Override
    public int evaluate(int x) {
        return -exp.evaluate(x);
    }

    @Override
    public BigInteger evaluate(BigInteger x) {
        throw new UnsupportedOperationException("Unary Minus does not support BigIntegers");
    }
}