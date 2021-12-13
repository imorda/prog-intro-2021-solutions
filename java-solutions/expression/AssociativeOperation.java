package expression;

public abstract class AssociativeOperation extends BinaryOperation {
    public AssociativeOperation(PriorityExpression left, PriorityExpression right, int priority, int localPriority) {
        super(left, right, priority, localPriority);
    }

    @Override
    protected void serializeMini(StringBuilder sb) {
        serializeMiniBinary(sb, true);
    }
}