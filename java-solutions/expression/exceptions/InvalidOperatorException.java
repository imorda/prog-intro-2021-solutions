package expression.exceptions;

public final class InvalidOperatorException extends ExpressionParseException {
    public InvalidOperatorException(CharSource source, String got) {
        super("expected valid operator", source, got);
    }
}
