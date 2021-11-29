package expression;

public class Divide extends NonCommutativeOperation {
    public Divide(PriorityExpression left, PriorityExpression right) {
        super(left, right, 0, -1);
    }

    public String getBinaryOperationSymbol() {
        return "/";
    }

    @Override
    public int evaluate(int x) {
        return left.evaluate(x) / right.evaluate(x);
    }
}