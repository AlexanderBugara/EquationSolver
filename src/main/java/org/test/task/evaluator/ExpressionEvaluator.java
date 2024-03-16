package org.test.task.evaluator;
import java.text.NumberFormat;
import java.util.*;

public class ExpressionEvaluator {
    private Tokenizer tokenizer;
    private HashMap<String, List<Operator>> operators = new HashMap<>();
    private static final ThreadLocal<NumberFormat> FORMATTER = new ThreadLocal<NumberFormat>() {
        @Override
        protected NumberFormat initialValue() {
            return NumberFormat.getNumberInstance(Locale.US);
        }
    };

    public Double evaluate(String expression, Variable variable) {
        final Iterator<String> iterator = tokenizer.tokenize(expression, variable);
        Token previous = null;
        final Deque<Double> values = new ArrayDeque<Double>();
        final Deque<Token> stack = new ArrayDeque<Token>();
        while (iterator.hasNext()) {
            String strToken = iterator.next();
            final Token token = toToken(previous, strToken);
            if (token.isOpenBracket()) {
                stack.push(token);
            } else if (token.isCloseBracket() && previous == null) {
                throw new IllegalArgumentException("expression can't start with a close bracket");
            } else if (token.isCloseBracket()) {
                BracketPair brackets = token.getBrackets();
                boolean openBracketFound = false;
                while (!stack.isEmpty()) {
                    Token tk = stack.pop();
                    if (tk.isOpenBracket() && tk.getBrackets().equals(brackets)) {
                        openBracketFound = true;
                        break;
                    } else if (tk.isOpenBracket()) {
                        throw new IllegalArgumentException("Invalid parenthesis match " + tk.getBrackets().getOpen() + brackets.getClose());
                    } else {
                        output(values, tk);
                    }
                }
                if (!openBracketFound) {
                    throw new IllegalArgumentException("Can't find open parentheses");
                }
            } else if (token.isOperator()) {
                while (!stack.isEmpty()) {
                    Token tk = stack.peek();
                    if (operatorCondition(tk, token)) {
                        output(values, stack.pop());
                    } else {
                        break;
                    }
                }
                stack.push(token);
            } else {
                if ((previous != null) && previous.isLiteral()) {
                    throw new IllegalArgumentException("A literal can't follow another literal");
                }
                output(values, token);
            }
            previous = token;
        }

        while (!stack.isEmpty()) {
            Token tk = stack.pop();
            if (tk.isOpenBracket() || tk.isCloseBracket()) {
                throw new IllegalArgumentException("Parentheses mismatched");
            }
            output(values, tk);
        }
        if (values.size() != 1) {
            throw new IllegalArgumentException();
        }
        return values.pop();
    }

    private boolean operatorCondition(Token tk, Token token) {
        return tk.isOperator() && ((token.getAssociativity().equals(Operator.Associativity.LEFT) &&
                (token.getPrecedence() <= tk.getPrecedence())) || (token.getPrecedence() < tk.getPrecedence()));
    }

    private Double evaluate(Operator operator, Iterator<Double> operands) {
        if (Tokenizer.Kind.NEGATIVE_OP.equals(operator)) {
            return -operands.next();
        } else if (Tokenizer.Kind.MINUS_OP.equals(operator)) {
            return operands.next() - operands.next();
        } else if (Tokenizer.Kind.PLUS_OP.equals(operator)) {
            return operands.next() + operands.next();
        } else if (Tokenizer.Kind.MULTIPLY_OP.equals(operator)) {
            return operands.next() * operands.next();
        } else if (Tokenizer.Kind.DIVIDE_OP.equals(operator)) {
            Double next = operands.next();
            Double nextNext = operands.next();
            if (nextNext == 0.0) {
                throw new IllegalArgumentException("Division by zero");
            }
            return next / nextNext;
        } else {
            throw new RuntimeException("evaluate(Operator, Iterator) is not implemented for " + operator.getSymbol());
        }
    }

    private void output(Deque<Double> values, Token token) {
        if (token.isLiteral()) {
            String literal = token.getLiteral();
            values.push(Double.valueOf(literal));
        } else if (token.isOperator()) {
            Operator operator = token.getOperator();
            values.push(evaluate(operator, getArguments(values, operator.getOperandCount())));
        } else {
            throw new IllegalArgumentException();
        }
    }

    private Iterator<Double> getArguments(Deque<Double> values, int nb) {
        if (values.size() < nb) {
            throw new IllegalArgumentException();
        }

        LinkedList<Double> result = new LinkedList<>();

        for (int i = 0; i < nb; i++) {
            result.addFirst(values.pop());
        }
        return result.iterator();
    }

    public ExpressionEvaluator(Tokenizer tokenizer) {
        if (tokenizer == null) {
            this.tokenizer = new Tokenizer();
        } else {
            this.tokenizer = tokenizer;
        }

        List<Operator> list = new ArrayList<>();
        // each token can be treated as different type of operations, that is why value is a List of operations.
        operators.put(Tokenizer.Kind.MINUS.asString(), Tokenizer.Kind.MINUS.asOperatorList());
        operators.put(Tokenizer.Kind.PLUS.asString(),  Tokenizer.Kind.PLUS.asOperatorList());
        operators.put(Tokenizer.Kind.MULTIPLICATION.asString(), Tokenizer.Kind.MULTIPLICATION.asOperatorList());
        operators.put(Tokenizer.Kind.DIVISION.asString(), Tokenizer.Kind.DIVISION.asOperatorList());
    }

    private Token toToken(Token previous, String token) {
        if (operators.get(token) != null) {
            List<Operator> list = operators.get(token);
            if (list.size() == 1) {
               return Token.buildOperator(list.get(0));
            }
            Operator operator = pickOperator(previous, list);
            return Token.buildOperator(operator);
        } else if (Objects.equals(token, Tokenizer.Kind.OPEN_BRACKET.asString())) {
            return Token.buildOpenToken(BracketPair.PARENTHESES);
        } else if (Objects.equals(token, Tokenizer.Kind.CLOSE_BRACKET.asString())) {
            return Token.buildCloseToken(BracketPair.PARENTHESES);
        } else {
            return Token.buildLiteral(token);
        }

    }
    protected Operator pickOperator(Token previous, List<Operator> candidates) {
        final int argCount = ((previous != null) && (previous.isCloseBracket() || previous.isLiteral())) ? 2 : 1;
        for (Operator operator : candidates) {
            if (operator.getOperandCount() == argCount) {
                return operator;
            }
        }
        return null;
    }
}
