package expression.exceptions;

import expression.Add;
import expression.PriorityExpression;

import java.math.BigInteger;

public class CheckedAdd extends Add {
    public CheckedAdd(PriorityExpression left, PriorityExpression right) {
        super(left, right);
    }

    @Override
    public BigInteger evaluate(BigInteger x) {
        throw new UnsupportedOperationException("BigInteger is unsupported for checked expressions");
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return Math.addExact(left.evaluate(x, y, z), right.evaluate(x, y, z));
    }

    @Override
    public int evaluate(int x) {
        return Math.addExact(left.evaluate(x), right.evaluate(x));
    }
}