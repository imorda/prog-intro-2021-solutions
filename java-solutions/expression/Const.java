package expression;

public class Const extends Operand {
    private final int value;

    public Const(int value) {
        super();
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public int evaluate(int x) {
        return value;
    }

    @Override
    protected boolean valueEqualsImpl(Object that) {
        if (that instanceof Const) {
            return this.value == ((Const) that).value;
        }
        return false;
    }


    @Override
    public int hashCode() {
        return value;
    }
}