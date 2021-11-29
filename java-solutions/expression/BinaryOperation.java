package expression;

import java.util.Objects;

public abstract class BinaryOperation extends PriorityExpression {
    protected final PriorityExpression left;
    protected final PriorityExpression right;

    public BinaryOperation(PriorityExpression left, PriorityExpression right, int priority, int localPriority) {
        super(priority, localPriority);
        this.left = Objects.requireNonNull(left);
        this.right = Objects.requireNonNull(right);
    }

    protected abstract String getBinaryOperationSymbol();

    @Override
    public String toString() {
        return "(" + left.toString() + " " + getBinaryOperationSymbol() + " " + right.toString() + ")";
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) return true;

        if (that != null) {
            if (this.getClass() == that.getClass()) {
                return this.left.equals(((BinaryOperation) that).left)
                        && this.right.equals(((BinaryOperation) that).right);
            }
        }
        return false;
    }


    @Override
    public int hashCode() {
        return Objects.hash(left, right, getBinaryOperationSymbol());
    }

    protected void appendOptionallyWithBrackets(StringBuilder sb, String data, boolean brackets) {
        if (brackets) {
            sb.append('(');
        }

        sb.append(data);

        if (brackets) {
            sb.append(')');
        }
    }
}