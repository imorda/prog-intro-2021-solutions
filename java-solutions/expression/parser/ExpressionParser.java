package expression.parser;

import expression.*;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public final class ExpressionParser implements Parser {
    @Override
    public TripleExpression parse(String expression) {
        return new ExpressionParserImpl(expression).parseExpression();
    }

    private static class ExpressionParserImpl extends BaseParser {
        private final Map<String, SupportedBinaryOperations> supportedBinOps = Map.of(
                Add.OPERATION_SYM, new SupportedBinaryOperations(Add::new, 2),
                Subtract.OPERATION_SYM, new SupportedBinaryOperations(Subtract::new, 2),
                Multiply.OPERATION_SYM, new SupportedBinaryOperations(Multiply::new, 1),
                Divide.OPERATION_SYM, new SupportedBinaryOperations(Divide::new, 1),
                Max.OPERATION_SYM, new SupportedBinaryOperations(Max::new, 3),
                Min.OPERATION_SYM, new SupportedBinaryOperations(Min::new, 3)
        );
        private final Map<String, SupportedUnaryOperations> supportedUnaryOps = Map.of(
                Negate.OPERATION_SYM, new SupportedUnaryOperations(Negate::new, 0),
                TZero.OPERATION_SYM, new SupportedUnaryOperations(TZero::new, 0),
                LZero.OPERATION_SYM, new SupportedUnaryOperations(LZero::new, 0)
        );
        private final String supportedVariables = "xXyYzZ";

        public ExpressionParserImpl(CharSource source) {
            super(source);
        }

        public ExpressionParserImpl(String source) {
            this(new StringSource(source));
        }

        private PriorityExpression parseExpression() {
            PriorityExpression res = parseOperation();
            skipWhitespace();
            if (eof()) {
                return res;
            }
            throw error("Expected end-of-file");
        }

        private PriorityExpression parseOperation() {
            return parseOperation(null, null);
        }

        private PriorityExpression parseOperation(PriorityExpression prevOperand,
                                                  SupportedBinaryOperations prevOperator) {
            skipWhitespace();

            if (prevOperand == null) {
                prevOperand = parseOperand();
            }

            skipWhitespace();

            if (prevOperator == null) {
                prevOperator = takeBinaryOperator();
            }

            skipWhitespace();
            PriorityExpression rightOperand = parseOperand();
            skipWhitespace();

            if (eof() || take(')')) {
                if (prevOperator == null) {
                    return prevOperand;
                }
                return prevOperator.expConstructor().apply(prevOperand, rightOperand);
            }

            skipWhitespace();
            SupportedBinaryOperations nextOp = takeBinaryOperator();
            skipWhitespace();

            if (nextOp == null || prevOperator == null) {
                throw error("No operator found!");
            }

            if (nextOp.priority() < prevOperator.priority()) {
                return prevOperator.expConstructor().apply(prevOperand, parseOperation(rightOperand, nextOp));
            }
            return parseOperation(prevOperator.expConstructor().apply(prevOperand, rightOperand), nextOp);
        }

        private PriorityExpression parseOperand() {
            skipWhitespace();
            if (take('(')) {
                return parseOperation();
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
            return null;
        }

        private PriorityExpression tryExtractConst(StringBuilder number) {
            takeInteger(number);
            try {
                return new Const(Integer.parseInt(number.toString()));
            } catch (final NumberFormatException e) {
                throw error("Invalid number " + number);
            }
        }

        private void takeInteger(StringBuilder sb) {
            if (take('0')) {
                sb.append('0');
            } else if (between('1', '9')) {
                while (between('0', '9')) {
                    sb.append(take());
                }
            } else {
                throw error("Integer expected");
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
