package expression;

public class Multiply extends CommutativeOperation {
    public Multiply(PriorityExpression left, PriorityExpression right) {
        super(left, right, 0, 0);
    }

    public String getBinaryOperationSymbol() {
        return "*";
    }

    @Override
    public int evaluate(int x) {
        return left.evaluate(x) * right.evaluate(x);
    }
}