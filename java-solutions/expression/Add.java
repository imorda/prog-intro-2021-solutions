package expression;

public final class Add extends CommutativeOperation {
    public Add(PriorityExpression left, PriorityExpression right) {
        super(left, right, 1, 0);
    }

    public String getBinaryOperationSymbol() {
        return "+";
    }

    @Override
    public int evaluate(int x) {
        return left.evaluate(x) + right.evaluate(x);
    }
}