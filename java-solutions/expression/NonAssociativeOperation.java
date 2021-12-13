package expression;

public abstract class NonAssociativeOperation extends BinaryOperation {
    public NonAssociativeOperation(PriorityExpression left, PriorityExpression right, int priority, int localPriority) {
        super(left, right, priority, localPriority);
    }

    @Override
    protected void serializeMini(StringBuilder sb) {
        serializeMiniBinary(sb, false);
    }
}