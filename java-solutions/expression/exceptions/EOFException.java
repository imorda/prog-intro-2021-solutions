package expression.exceptions;

public final class EOFException extends ParseException {
    public EOFException(CharSource source) {
        super("Unexpected end-of-file", source);
    }
}
