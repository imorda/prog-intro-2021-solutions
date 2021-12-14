package expression.exceptions;

import expression.Multiply;
import expression.PriorityExpression;

import java.math.BigInteger;

public class CheckedMultiply extends Multiply {
    public CheckedMultiply(PriorityExpression left, PriorityExpression right) {
        super(left, right);
    }

    @Override
    public BigInteger evaluate(BigInteger x) {
        throw new UnsupportedOperationException("BigInteger is unsupported for checked expressions");
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return Math.multiplyExact(left.evaluate(x, y, z), right.evaluate(x, y, z));
    }

    @Override
    public int evaluate(int x) {
        return Math.multiplyExact(left.evaluate(x), right.evaluate(x));
    }
}