package org.test.task.evaluator;

public class BracketPair {
    public static final BracketPair PARENTHESES = new BracketPair('(', ')');
    private String open;
    private String close;

    public BracketPair(char open, char close) {
        super();
        this.open = new String(new char[]{ open });
        this.close = new String(new char[]{ close });
    }

    public String getOpen() {
        return open;
    }

    public String getClose() {
        return close;
    }

    @Override
    public String toString() {
        return open + close;
    }
}