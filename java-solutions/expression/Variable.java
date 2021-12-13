package expression;

import java.math.BigInteger;

public final class Variable extends Operand {
    private final static String allowedSymbols = "xyz";
    private final char symbol;

    public Variable(String symbol) {
        if (symbol.length() != 1 || !allowedSymbols.contains(symbol.toLowerCase())) {
            throw new IllegalArgumentException("Only X,Y,Z allowed as Const symbol, given " + symbol);
        }
        this.symbol = symbol.toLowerCase().charAt(0);
    }

    @Override
    protected void serializeString(StringBuilder sb) {
        sb.append(symbol);
    }

    @Override
    protected void serializeMini(StringBuilder sb) {
        sb.append(symbol);
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public BigInteger evaluate(BigInteger x) {
        return x;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return switch (symbol) {
            case 'x' -> x;
            case 'y' -> y;
            case 'z' -> z;
            default -> throw new IllegalStateException("Invalid Const symbol: " + symbol);
        };
    }

    @Override
    protected boolean valueEqualsImpl(Object that) {
        if (that instanceof Variable) {
            return this.symbol == ((Variable) that).symbol;
        }
        return false;
    }

    @Override
    public int hashCodeImpl() {
        return symbol;
    }
}