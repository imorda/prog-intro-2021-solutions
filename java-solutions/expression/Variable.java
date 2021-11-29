package expression;

public final class Variable extends Operand {
    private final String symbol;

    public Variable(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    protected boolean valueEqualsImpl(Object that) {
        if (that instanceof Variable) {
            return this.symbol.equals(((Variable) that).symbol);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return symbol.hashCode();
    }
}