package expression;

public abstract class Operand extends PriorityExpression {
    public Operand() {
        super(-1, 0);
    }

    protected abstract boolean valueEqualsImpl(Object that);

    @Override
    public boolean equals(Object that) {
        if (this == that) return true;

        if (that != null) {
            if (this.getClass() == that.getClass()) {
                return this.valueEqualsImpl(that);
            }
        }
        return false;
    }
}