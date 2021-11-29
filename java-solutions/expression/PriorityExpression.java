package expression;

public abstract class PriorityExpression implements Expression, ToMiniString {
    private final int priority;
    private final int localPriority;

    public PriorityExpression(int priority, int localPriority) {
        this.priority = priority;
        this.localPriority = localPriority;
    }

    protected int getPriority() {
        return priority;
    }

    protected int getLocalPriority() {
        return localPriority;
    }
}