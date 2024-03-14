package org.test.task.preprocessor;

public class Operation implements Element {
    private final char operator;
    private final Element left;
    private final Element right;

    public static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }
    public Operation(char operator, Element left, Element right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    @Override
    public double evaluate() {
        switch (operator) {
            case '+': return left.evaluate() + right.evaluate();
            case '-': return left.evaluate() - right.evaluate();
            case '*': return left.evaluate() * right.evaluate();
            case '/': return left.evaluate() / right.evaluate();
            default: throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
}