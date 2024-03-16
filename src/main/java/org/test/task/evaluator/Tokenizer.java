package org.test.task.evaluator;

import java.util.*;

public class Tokenizer {
    public enum Kind {
        OPEN_BRACKET,
        CLOSE_BRACKET,
        PLUS,
        MINUS,
        MULTIPLICATION,
        DIVISION;

        String asString() {
            switch (this) {
                case OPEN_BRACKET: return "(";
                case CLOSE_BRACKET: return ")";
                case MINUS: return "-";
                case PLUS: return "+";
                case MULTIPLICATION: return "*";
                case DIVISION: return "/";
            }
            return null;
        }

        List<Operator> asOperatorList() {
            switch (this) {
                case MINUS: {
                    List<Operator> list = new ArrayList<>();
                    list.add(NEGATIVE_OP);
                    list.add(MINUS_OP);
                    return list;
                }
                case PLUS: {
                    List<Operator> list = new ArrayList<>();
                    list.add(PLUS_OP);
                    return list;
                }
                case MULTIPLICATION: {
                    List<Operator> list = new ArrayList<>();
                    list.add(MULTIPLY_OP);
                    return list;
                }
                case DIVISION: {
                    List<Operator> list = new ArrayList<>();
                    list.add(DIVIDE_OP);
                    return list;
                }
            }
            return null;
        }

        public static final Operator NEGATIVE_OP = new Operator("-", 1, Operator.Associativity.RIGHT, 3);
        public static final Operator MINUS_OP = new Operator("-", 2, Operator.Associativity.LEFT, 1);
        public static final Operator PLUS_OP = new Operator("+", 2, Operator.Associativity.LEFT, 1);
        public static final Operator MULTIPLY_OP = new Operator("*", 2, Operator.Associativity.LEFT, 2);
        public static final Operator DIVIDE_OP = new Operator("/", 2, Operator.Associativity.LEFT, 2);

        static  Boolean contains(String topken) {
            for (Kind kind : Kind.values()) {
                if (Objects.equals(kind.toString(), topken)) {
                    return true;
                }
            }
            return false;
        }
    }

    public Tokenizer() {
    }

    public Iterator<String> tokenize(String string, Variable variable) {
        StringBuilder builder = new StringBuilder();
        for (Kind kind : Kind.values()) {
            builder.append(kind.asString());
        }
        return new StringTokenizerIterator(new StringTokenizer(string, builder.toString(), true), variable);
    }

    private class StringTokenizerIterator implements Iterator<String> {
        private StringTokenizer tokens;
        private Variable variable;
        public StringTokenizerIterator(StringTokenizer tokens, Variable variable) {
            this.tokens = tokens;
            this.variable = variable;
        }
        private String nextToken = null;
        @Override
        public boolean hasNext() {
            return buildNextToken();
        }
        @Override
        public String next() {
            if (!buildNextToken()) {
                throw new NoSuchElementException();
            }
            String token = nextToken;
            nextToken = null;

            if (variable != null && token.equals(variable.name())) {
                return String.valueOf(variable.get());
            }

            return token;
        }
        private boolean buildNextToken() {
            while ((nextToken == null) && tokens.hasMoreTokens()) {
                nextToken = tokens.nextToken();
                if (nextToken.isEmpty()) {
                    nextToken = null;
                }
            }
            return nextToken != null;
        }
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
