package expression.exceptions;

import expression.PriorityExpression;
import expression.Subtract;

import java.math.BigInteger;

public class CheckedSubtract extends Subtract {
    public CheckedSubtract(PriorityExpression left, PriorityExpression right) {
        super(left, right);
    }

    @Override
    public BigInteger evaluate(BigInteger x) {
        throw new UnsupportedOperationException("BigInteger is unsupported for checked expressions");
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return Math.subtractExact(left.evaluate(x, y, z), right.evaluate(x, y, z));
    }

    @Override
    public int evaluate(int x) {
        return Math.subtractExact(left.evaluate(x), right.evaluate(x));
    }
}