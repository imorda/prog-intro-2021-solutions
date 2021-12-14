package expression.exceptions;

import expression.Negate;
import expression.PriorityExpression;

import java.math.BigInteger;

public class CheckedNegate extends Negate {
    public CheckedNegate(PriorityExpression exp) {
        super(exp);
    }

    @Override
    public BigInteger evaluate(BigInteger x) {
        throw new UnsupportedOperationException("BigInteger is unsupported for checked expressions");
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return Math.negateExact(exp.evaluate(x, y, z));
    }

    @Override
    public int evaluate(int x) {
        return Math.negateExact(exp.evaluate(x));
    }
}