package expression;

public abstract class PriorityExpression implements Expression, TripleExpression, BigIntegerExpression, ToMiniString {
    private final int priority;
    private final int localPriority;

    protected Integer hashCache = null;

    public PriorityExpression(int priority, int localPriority) {
        this.priority = priority;
        this.localPriority = localPriority;
    }

    protected int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        serializeString(sb);
        return sb.toString();
    }

    @Override
    public String toMiniString() {
        StringBuilder sb = new StringBuilder();
        serializeMini(sb);
        return sb.toString();
    }

    @Override
    public int hashCode() {
        if(hashCache == null){
            hashCache = hashCodeImpl();
        }
        return hashCache;
    }

    protected abstract int hashCodeImpl();

    protected int getLocalPriority() {
        return localPriority;
    }

    protected abstract void serializeMini(StringBuilder sb);

    protected abstract void serializeString(StringBuilder sb);
}