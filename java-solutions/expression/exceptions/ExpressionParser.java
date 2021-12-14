package expression.exceptions;

import expression.*;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public final class ExpressionParser implements ExceptionParser {
    @Override
    public TripleExpression parse(String expression) throws ParseException {
        return new ExpressionParserImpl(expression).parseExpression();
    }

    private static class ExpressionParserImpl extends BaseParser {
        private final Map<String, SupportedBinaryOperations> supportedBinOps = Map.of(
                "+", new SupportedBinaryOperations(CheckedAdd::new, 2),
                CheckedSubtract.operationSym, new SupportedBinaryOperations(CheckedSubtract::new, 2),
                CheckedMultiply.operationSym, new SupportedBinaryOperations(CheckedMultiply::new, 1),
                CheckedDivide.operationSym, new SupportedBinaryOperations(CheckedDivide::new, 1),
                Max.operationSym, new SupportedBinaryOperations(Max::new, 3),
                Min.operationSym, new SupportedBinaryOperations(Min::new, 3)
        );
        private final Map<String, SupportedUnaryOperations> supportedUnaryOps = Map.of(
                CheckedNegate.operationSym, new SupportedUnaryOperations(CheckedNegate::new, 0),
                TZero.operationSym, new SupportedUnaryOperations(TZero::new, 0),
                LZero.operationSym, new SupportedUnaryOperations(LZero::new, 0)
        );
        private final String supportedVariables = "xXyYzZ";

        public ExpressionParserImpl(CharSource source) {
            super(source);
        }

        public ExpressionParserImpl(String source) {
            this(new StringSource(source));
        }

        private PriorityExpression parseExpression() throws ParseException {
            PriorityExpression res = parseOperation(false);
            skipWhitespace();
            if (eof()) {
                return res;
            }
            throw new EOFException(source);
        }

        private PriorityExpression parseOperation(boolean startsWithBracket) throws ParseException {
            return parseOperation(null, null, startsWithBracket);
        }

        private PriorityExpression parseOperation(PriorityExpression prevOperand,
                                                  SupportedBinaryOperations prevOperator,
                                                  boolean startsWithBracket) throws ParseException {
            skipWhitespace();

            if (prevOperand == null) {
                prevOperand = parseOperand();
            }

            skipWhitespace();

            if (prevOperator == null) {
                prevOperator = takeBinaryOperator();
            }

            skipWhitespace();
            PriorityExpression rightOperand = null;
            if (prevOperator != null) {
                rightOperand = parseOperand();
            }
            skipWhitespace();

            if ((!startsWithBracket && eof()) || (startsWithBracket && take(')'))) {
                if (prevOperator == null) {
                    return prevOperand;
                }
                return prevOperator.expConstructor().apply(prevOperand, rightOperand);
            }

            skipWhitespace();
            SupportedBinaryOperations nextOp = takeBinaryOperator();
            skipWhitespace();

            if (nextOp == null || prevOperator == null) {
                throw new InvalidOperatorException(source, peekOrEOF());
            }

            if (nextOp.priority() < prevOperator.priority()) {
                return prevOperator.expConstructor().apply(prevOperand, parseOperation(rightOperand, nextOp,
                        startsWithBracket));
            }
            return parseOperation(prevOperator.expConstructor().apply(prevOperand, rightOperand),
                    nextOp, startsWithBracket);
        }

        private PriorityExpression parseOperand() throws ParseException {
            skipWhitespace();
            if (take('(')) {
                return parseOperation(true);
            }
            if (test(Character.DECIMAL_DIGIT_NUMBER)) {
                StringBuilder number = new StringBuilder();
                return tryExtractConst(number);
            }

            if (test(x -> supportedVariables.indexOf(x) > -1)) {
                char varSymbol = take();
                return new Variable(String.valueOf(varSymbol));
            }
            SupportedUnaryOperations operator = takeUnaryOperator();
            if (operator != null) {
                if (operator == supportedUnaryOps.get("-") && test(Character.DECIMAL_DIGIT_NUMBER)) {
                    StringBuilder number = new StringBuilder().append('-');
                    return tryExtractConst(number);
                }

                if (test('(')) {
                    return operator.expConstructor().apply(new BraceEnclosed(parseOperand()));
                }

                return operator.expConstructor().apply(parseOperand());
            }
            throw new InvalidOperandException(source, peekOrEOF());
        }

        private PriorityExpression tryExtractConst(StringBuilder number) throws ParseException {
            takeInteger(number);
            try {
                return new Const(Integer.parseInt(number.toString()));
            } catch (final NumberFormatException e) {
                throw new NumberParseException(source, number.toString());
            }
        }

        private void takeInteger(StringBuilder sb) throws ParseException {
            if (take('0')) {
                sb.append('0');
            } else if (between('1', '9')) {
                while (between('0', '9')) {
                    sb.append(take());
                }
            } else {
                if (!eof()) {
                    sb.append(take());
                }
                throw new NumberParseException(source, sb.toString());
            }
        }

        private SupportedBinaryOperations takeBinaryOperator() {
            for (Map.Entry<String, SupportedBinaryOperations> i : supportedBinOps.entrySet()) {
                if (take(i.getKey())) {
                    return i.getValue();
                }
            }
            return null;
        }

        private SupportedUnaryOperations takeUnaryOperator() {
            for (Map.Entry<String, SupportedUnaryOperations> i : supportedUnaryOps.entrySet()) {
                if (take(i.getKey())) {
                    return i.getValue();
                }
            }
            return null;
        }

        record SupportedBinaryOperations(
                BiFunction<PriorityExpression, PriorityExpression, PriorityExpression> expConstructor,
                int priority
        ) {
        }

        record SupportedUnaryOperations(
                Function<PriorityExpression, PriorityExpression> expConstructor,
                int priority
        ) {
        }
    }
}
