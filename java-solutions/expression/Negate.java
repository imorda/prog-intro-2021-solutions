package expression;

import java.math.BigInteger;

public class Negate extends UnaryOperation {
    public Negate(PriorityExpression exp) {
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
        return "-";
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
        throw new UnsupportedOperationException("Negate does not support BigIntegers");
    }
}