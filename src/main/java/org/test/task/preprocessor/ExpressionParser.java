package org.test.task.preprocessor;

public class ExpressionParser {
    private final String input;
    private int index;

    public ExpressionParser(String input) {
        this.input = input.replaceAll("\\s+", "");
        this.index = 0;
    }

    public Element parse() {
        return expression();
    }

    private Element expression() {
        Element left = term();
        while (index < input.length() && (input.charAt(index) == '+' || input.charAt(index) == '-')) {
            char operator = input.charAt(index++);
            Element right = term();
            left = new Operation(operator, left, right);
        }
        return left;
    }

    private Element term() {
        Element left = factor();
        while (index < input.length() && (input.charAt(index) == '*' || input.charAt(index) == '/')) {
            char operator = input.charAt(index++);
            Element right = factor();
            left = new Operation(operator, left, right);
        }
        return left;
    }

    private Element factor() {
        char ch = input.charAt(index);
        if (ch == '(') {
            index++;
            Element expression = expression();
            if (input.charAt(index++) != ')') {
                throw new IllegalArgumentException("Mismatched parentheses");
            }
            return expression;
        } else if (Character.isDigit(ch)) {
            SequenceToValueConverter converter = new SequenceToValueConverter(ch, index, input);
            index = converter.getLastIndex();
            return new Operand(converter.getValue());
        } else {
            throw new IllegalArgumentException("Invalid character: " + ch);
        }
    }
}
