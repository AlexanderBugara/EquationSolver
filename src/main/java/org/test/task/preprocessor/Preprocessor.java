package org.test.task.preprocessor;

public class Preprocessor {
    private Rule[] rules;

    public Rule[] getRules() {
        return rules;
    }

    public Boolean notPassed(String equation) {
        char[] characters = equation.toCharArray();
        for (char character : characters) {
            for (Rule rule : rules) {
                rule.next(character);
            }
        }
        boolean result = false;
        for (Rule rule : rules) {
            if (!rule.isSuccess()) {
                result = true;
                break;
            }
        }
        return result;
    }

    public Preprocessor() {
        initRules();
    }

    private void initRules() {
        ParenthesesOpenCloseRule parenthesesRule = new ParenthesesOpenCloseRule();
        rules[0] = parenthesesRule;
    }

    public String[] getErrorMessages() {
        String[] result = new String[rules.length];
        for (int i = 0; i < rules.length; i++) {
            result[i] = rules[i].getErrorMessage();
        }
        return result;
    }
}
