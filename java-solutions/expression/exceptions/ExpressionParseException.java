package expression.exceptions;

public class ExpressionParseException extends ParseException {
    private final String got;

    public ExpressionParseException(String message, CharSource source, String got) {
        super(message + "; got '" + got + "'", source);
        this.got = got;
    }

    public String getGot() {
        return got;
    }
}
