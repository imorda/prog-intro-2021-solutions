package expression;

import java.math.BigInteger;

public class Divide extends NonCommutativeOperation {
    public Divide(PriorityExpression left, PriorityExpression right) {
        super(left, right, 0, -1);
    }

    public String getBinaryOperationSymbol() {
        return "/";
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