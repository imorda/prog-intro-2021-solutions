package expression;

import java.util.Objects;

public abstract class UnaryOperation extends PriorityExpression {
    protected final PriorityExpression exp;

    public UnaryOperation(PriorityExpression exp, int priority, int localPriority) {
        super(priority, localPriority);
        this.exp = Objects.requireNonNull(exp);
    }

    protected abstract String getUnaryOperationSymbol();

    @Override
    public String toString() {
        return getUnaryOperationSymbol() + exp.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) return true;

        if (that != null) {
            if (this.getClass() == that.getClass()) {
                return this.exp.equals(((UnaryOperation) that).exp);
            }
        }
        return false;
    }


    @Override
    public int hashCode() {
        return Objects.hash(exp, getUnaryOperationSymbol());
    }

    @Override
    public String toMiniString() {
        if (exp.getPriority() <= getPriority()) {
            return getUnaryOperationSymbol() + " " + exp.toMiniString();
        } else {
            return getUnaryOperationSymbol() + "(" + exp.toMiniString() + ")";
        }
    }
}