package expression;

import java.math.BigInteger;

public class Const extends Operand {
    private final Number value;

    public Const(int value) {
        super();
        this.value = value;
    }

    public Const(BigInteger value) {
        super();
        this.value = value;
    }

    public Number getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public BigInteger evaluate(BigInteger x) {
        if (value instanceof BigInteger) {
            return (BigInteger) value;
        }
        return BigInteger.valueOf(value.longValue());
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return evaluate(x);
    }

    @Override
    public int evaluate(int x) {
        return value.intValue();
    }

    @Override
    protected boolean valueEqualsImpl(Object that) {
        if (that instanceof Const) {
            return this.value.equals(((Const) that).value);
        }
        return false;
    }


    @Override
    public int hashCode() {
        return value.hashCode();
    }
}