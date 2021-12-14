package expression.exceptions;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface CharSource {
    boolean hasNext();

    char next();

    void mark();

    void unmark();

    void returnToMark();

    int getOffset();
}
