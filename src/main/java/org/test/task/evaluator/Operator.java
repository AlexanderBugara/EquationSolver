package org.test.task.evaluator;

public class Operator {
    public enum Associativity {
        LEFT,
        RIGHT,
        NONE
    }
    private String symbol;
    private int precedence;
    private int operandCount;
    private Operator.Associativity associativity;

    
    public Operator(String symbol, int operandCount, Operator.Associativity associativity, int precedence) {
        if (symbol == null || associativity == null) {
            throw new NullPointerException();
        }

        if (symbol.isEmpty()) {
            throw new IllegalArgumentException("Operator symbol can't be null");
        }

        if ((operandCount < 1) || (operandCount > 2)) {
            throw new IllegalArgumentException("Only unary and binary operators are supported");
        }
        if (Operator.Associativity.NONE.equals(associativity)) {
            throw new IllegalArgumentException("None associativity operators are not supported");
        }
        this.symbol = symbol;
        this.operandCount = operandCount;
        this.associativity = associativity;
        this.precedence = precedence;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public int getOperandCount() {
        return this.operandCount;
    }

    public Operator.Associativity getAssociativity() {
        return this.associativity;
    }

    public int getPrecedence() {
        return this.precedence;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + operandCount;
        result = prime * result + ((associativity == null) ? 0 : associativity.hashCode());
        result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
        result = prime * result + precedence;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj == null) || (obj instanceof Operator)) {
            return false;
        }
        Operator other = (Operator) obj;
        if ((operandCount != other.operandCount) || (associativity != other.associativity)) {
            return false;
        }
        if (symbol == null && other.symbol != null) {
            return false;
        } else if (!symbol.equals(other.symbol)) {
            return false;
        }
        return precedence == other.precedence;
    }
}