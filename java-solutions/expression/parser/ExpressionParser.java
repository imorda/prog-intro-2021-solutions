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
                Add.operationSym, new SupportedBinaryOperations(Add::new, 2),
                Subtract.operationSym, new SupportedBinaryOperations(Subtract::new, 2),
                Multiply.operationSym, new SupportedBinaryOperations(Multiply::new, 1),
                Divide.operationSym, new SupportedBinaryOperations(Divide::new, 1)
        );
        private final Map<String, SupportedUnaryOperations> supportedUnaryOps = Map.of(
                UnaryMinus.operationSym, new SupportedUnaryOperations(UnaryMinus::new, 0)
        );
        private final String supportedVariables = "xXyYzZ";

        public ExpressionParserImpl(CharSource source) {
            super(source);
        }

        public ExpressionParserImpl(String source) {
            this(new StringSource(source));
        }

        private PriorityExpression parseExpression() {
            PriorityExpression res = parseElement();
            if (eof()) {
                return res;
            }
            throw error("Expected end-of-file");
        }

        private PriorityExpression parseElement() {
            skipWhitespace();
            if (eof() || take(')')) {
                throw error("Bracket-enclosed expression is empty.");
            }
            PriorityExpression operand = parseOperand();
            skipWhitespace();
            if (eof() || take(')')) {
                return operand;
            }

            String operator = takeBinaryOperator();
            if (operator == null) {
                throw error("Unrecognized operator");
            }
            if (eof() || take(')')) {
                throw error("Unexpected end of bracket-enclosed expression");
            }

            return parseOperation(operand, operator);
        }

        private PriorityExpression parseOperation(PriorityExpression leftOperand, String operator) {
            skipWhitespace();
            PriorityExpression right = parseOperand();
            skipWhitespace();

            if (eof() || take(')')) {
                return supportedBinOps.get(operator).expConstructor.apply(leftOperand, right);
            }

            skipWhitespace();
            String nextOp = takeBinaryOperator();
            skipWhitespace();

            SupportedBinaryOperations next = supportedBinOps.get(nextOp);
            SupportedBinaryOperations cur = supportedBinOps.get(operator);

            if (next.priority < cur.priority) {
                return cur.expConstructor.apply(leftOperand, parseOperation(right, nextOp));
            }
            return parseOperation(cur.expConstructor.apply(leftOperand, right), nextOp);
        }

        private PriorityExpression parseOperand() {
            skipWhitespace();
            if (take('(')) {
                return parseElement();
            }
            if (test(Character.DECIMAL_DIGIT_NUMBER)) {
                StringBuilder number = new StringBuilder();
                return tryExtractConst(number);
            }

            if (test((x) -> supportedVariables.indexOf(x) > -1)) {
                char varSymbol = take();
                return new Variable(String.valueOf(varSymbol));
            }

            String operator = takeUnaryOperator();
            if (operator != null) {
                if (test(Character.DECIMAL_DIGIT_NUMBER)) {
                    StringBuilder number = new StringBuilder().append(operator);
                    return tryExtractConst(number);
                }

                if (test('(')) {
                    return supportedUnaryOps.get(operator).expConstructor.apply(new BraceEnclosed(parseOperand()));
                }

                return supportedUnaryOps.get(operator).expConstructor.apply(parseOperand());
            }
            throw error("Unrecognized token");
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

        private String takeBinaryOperator() {
            if (take('+')) {
                return "+";
            }
            if (take('-')) {
                return "-";
            }
            if (take('*')) {
                return "*";
            }
            if (take('/')) {
                return "/";
            }
            return null;
        }

        private String takeUnaryOperator() {
            if (take('-')) {
                return "-";
            }
            return null;
        }

        final record SupportedBinaryOperations(
                BiFunction<PriorityExpression, PriorityExpression, PriorityExpression> expConstructor,
                int priority
        ) {
        }

        final record SupportedUnaryOperations(
                Function<PriorityExpression, PriorityExpression> expConstructor,
                int priority
        ) {
        }
    }
}
