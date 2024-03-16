package org.test.task.evaluator;

import org.test.task.evaluator.BracketPair;

public class Token {
    private enum Kind {
        OPEN_BRACKET,
        CLOSE_BRACKET,
        OPERATOR,
        LITERAL
    }
    private Kind kind;
    private Object content;

    public static Token buildLiteral(String literal) {
        return new Token(Kind.LITERAL, literal);
    }

    static Token buildOperator(Operator ope) {
        return new Token(Kind.OPERATOR, ope);
    }

    public static Token buildOpenToken(BracketPair pair) {
        return new Token(Kind.OPEN_BRACKET, pair);
    }

    public static Token buildCloseToken(BracketPair pair) {
        return new Token(Kind.CLOSE_BRACKET, pair);
    }

    private Token(Kind kind, Object content) {
        super();
        if ((kind.equals(Kind.OPERATOR) && !(content instanceof Operator)) ||
                (kind.equals(Kind.LITERAL)) && !(content instanceof String)) {
            throw new IllegalArgumentException();
        }
        this.kind = kind;
        this.content = content;
    }

    BracketPair getBrackets() {
        return (BracketPair) this.content;
    }

    Operator getOperator() {
        return (Operator) this.content;
    }

    Kind getKind() {
        return kind;
    }

    public boolean isOperator() {
        return kind.equals(Kind.OPERATOR);
    }

    public boolean isOpenBracket() {
        return kind.equals(Kind.OPEN_BRACKET);
    }

    public boolean isCloseBracket() {
        return kind.equals(Kind.CLOSE_BRACKET);
    }

    public boolean isLiteral() { return kind.equals(Kind.LITERAL); }

    Operator.Associativity getAssociativity() {
        return getOperator().getAssociativity();
    }

    int getPrecedence() {
        return getOperator().getPrecedence();
    }

    String getLiteral() {
        if (!this.kind.equals(Kind.LITERAL)) {
            throw new IllegalArgumentException();
        }
        return (String)this.content;
    }

    @Override
    public String toString() {
        return "Token [kind=" + kind + ", content=" + content + "]";
    }

}
