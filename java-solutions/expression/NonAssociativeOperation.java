package expression;

public abstract class NonAssociativeOperation extends BinaryOperation {
    public NonAssociativeOperation(PriorityExpression left, PriorityExpression right, int priority, int localPriority) {
        super(left, right, priority, localPriority);
    }

    @Override
    public String toMiniString() {
        StringBuilder sb = new StringBuilder();
        appendOptionallyWithBrackets(sb, left.toMiniString(), left.getPriority() > getPriority());
        sb.append(' ').append(getBinaryOperationSymbol()).append(' ');
        appendOptionallyWithBrackets(sb, right.toMiniString(), right.getPriority() >= getPriority()
                || right.getPriority() == getPriority() && right.getLocalPriority() < getLocalPriority());
        return sb.toString();
    }

}