package expression.exceptions;

import expression.TripleExpression;

public interface ExceptionParser extends Parser {
    TripleExpression parse(String expression) throws ParseException;
}
