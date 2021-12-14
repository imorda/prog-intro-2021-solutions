package expression.exceptions;

import expression.Divide;
import expression.PriorityExpression;

import java.math.BigInteger;

public class CheckedDivide extends Divide {
    public CheckedDivide(PriorityExpression left, PriorityExpression right) {
        super(left, right);
    }

    private static int divideExact(int a, int b) {
        if (a == Integer.MIN_VALUE && b == -1) {
            throw new ArithmeticException(String.format("32-bit integer overflow while dividing %d and %d", a, b));
        }
        return a / b;
    }

    @Override
    public BigInteger evaluate(BigInteger x) {
        throw new UnsupportedOperationException("BigInteger is unsupported for checked expressions");
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return divideExact(left.evaluate(x, y, z), right.evaluate(x, y, z));
    }

    @Override
    public int evaluate(int x) {
        return divideExact(left.evaluate(x), right.evaluate(x));
    }
}