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

    protected static void appendOptionallyWithBrackets(StringBuilder sb, PriorityExpression data, boolean brackets) {
        if (brackets) {
            sb.append('(');
        }

        data.serializeMini(sb);

        if (brackets) {
            sb.append(')');
        }
    }

    protected abstract String getBinaryOperationSymbol();

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
    public int hashCodeImpl() {
        return Objects.hash(left, right, getBinaryOperationSymbol());
    }

    @Override
    protected void serializeString(StringBuilder sb) {
        sb.append('(');
        left.serializeString(sb);
        sb.append(' ').append(getBinaryOperationSymbol()).append(' ');
        right.serializeString(sb);
        sb.append(')');
    }

    protected void serializeMiniBinary(StringBuilder sb, boolean isAssociative) {
        appendOptionallyWithBrackets(sb, left, left.getPriority() > getPriority());
        sb.append(' ').append(getBinaryOperationSymbol()).append(' ');
        boolean rightPriority = right.getPriority() > getPriority()
                || (!isAssociative && right.getPriority() == getPriority());
        appendOptionallyWithBrackets(sb, right, rightPriority
                || right.getPriority() == getPriority() && right.getLocalPriority() < getLocalPriority());
    }
}