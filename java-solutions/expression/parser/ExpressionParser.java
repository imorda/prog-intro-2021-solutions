package expression.parser;

import expression.*;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class ExpressionParser implements Parser {
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
        private final String supportedVariables = "xyz";

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
            return parseOperation(Integer.MAX_VALUE);
        }

        private PriorityExpression parseOperation(int maxPriority) {
            skipWhitespace();

            PriorityExpression leftOperand = parseOperand();
            while (true) {
                skipWhitespace();

                SupportedBinaryOperations operator = takeBinaryOperator(maxPriority);

                if (operator == null) {
                    return leftOperand;
                }

                skipWhitespace();
                PriorityExpression rightOperand = parseOperation(operator.priority() - 1);

                leftOperand = operator.expConstructor().apply(leftOperand, rightOperand);
            }
        }

        private PriorityExpression parseOperand() {
            skipWhitespace();
            if (take('(')) {
                PriorityExpression op = parseOperation();
                expect(')');
                return op;
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
            throw error("Unrecognized operand");
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

        private SupportedBinaryOperations takeBinaryOperator(int maxPriority) {
            for (Map.Entry<String, SupportedBinaryOperations> i : supportedBinOps.entrySet()) {
                if (i.getValue().priority() <= maxPriority && take(i.getKey())) {
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
