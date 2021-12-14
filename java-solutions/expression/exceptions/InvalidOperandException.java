package expression.exceptions;

public final class InvalidOperandException extends ExpressionParseException {
    public InvalidOperandException(CharSource source, String got) {
        super("expected valid operand", source, got);
    }
}
