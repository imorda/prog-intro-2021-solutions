package expression.exceptions;

import java.util.function.Predicate;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class BaseParser {
    private static final char END = '\0';
    protected final CharSource source;
    private char ch = 0xffff;

    protected BaseParser(final CharSource source) {
        this.source = source;
        take();
    }

    protected char take() {
        final char result = ch;
        ch = source.hasNext() ? source.next() : END;
        return result;
    }

    protected String peekOrEOF() {
        if (eof()) {
            return "end-of-file";
        }
        return String.valueOf(ch);
    }

    protected boolean test(final char expected) {
        return ch == expected;
    }

    protected boolean test(final Predicate<Character> predicate) {
        return predicate.test(ch);
    }

    protected boolean test(final int expectedType) {
        return Character.getType(ch) == expectedType;
    }

    protected boolean take(final char expected) {
        if (test(expected)) {
            take();
            return true;
        }
        return false;
    }

    protected boolean take(final String expected) {
        source.mark();
        final char startCh = ch;

        for (final char ch : expected.toCharArray()) {
            if (!take(ch)) {
                source.returnToMark();
                this.ch = startCh;
                return false;
            }
        }

        source.unmark();
        return true;
    }

    protected void skipWhitespace() {
        while (Character.isWhitespace(ch)) {
            take();
        }
    }

    protected boolean eof() {
        return take(END);
    }

    protected boolean between(final char from, final char to) {
        return from <= ch && ch <= to;
    }
}
