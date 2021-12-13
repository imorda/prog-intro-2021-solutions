package expression;

import java.math.BigInteger;

public final class BraceEnclosed extends UnaryOperation {
    public BraceEnclosed(PriorityExpression exp) {
        super(exp, exp.getPriority(), 0);
    }

    @Override
    public String toMiniString() {
        return exp.toMiniString();
    }

    @Override
    protected void serializeString(StringBuilder sb) {
        exp.serializeString(sb);
    }

    @Override
    protected void serializeMini(StringBuilder sb) {
        exp.serializeMini(sb);
    }

    @Override
    public String getUnaryOperationSymbol() {
        return "";
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return exp.evaluate(x, y, z);
    }

    @Override
    public int evaluate(int x) {
        return exp.evaluate(x);
    }

    @Override
    public BigInteger evaluate(BigInteger x) {
        throw new UnsupportedOperationException("Unary Minus does not support BigIntegers");
    }
}