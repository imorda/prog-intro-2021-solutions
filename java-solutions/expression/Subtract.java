package expression;

public class Subtract extends NonCommutativeOperation {
    public Subtract(PriorityExpression left, PriorityExpression right) {
        super(left, right, 1, 0);
    }

    public String getBinaryOperationSymbol() {
        return "-";
    }

    @Override
    public int evaluate(int x) {
        return left.evaluate(x) - right.evaluate(x);
    }
}