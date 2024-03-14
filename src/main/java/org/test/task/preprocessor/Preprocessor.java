package org.test.task.preprocessor;

import java.util.ArrayList;

public class Preprocessor {
    private ArrayList<Rule> rules;

//    public ArrayList<Rule> getRules() {
//        return rules;
//    }
//
//    public Boolean notPassed(String equation) {
//        char[] characters = equation.toCharArray();
//        for (char character : characters) {
//            for (Rule rule : rules) {
//                rule.next(character);
//            }
//        }
//        boolean result = false;
//        for (Rule rule : rules) {
//            if (!rule.isSuccess()) {
//                result = true;
//                break;
//            }
//        }
//        return result;
//    }
//
//    public Preprocessor() {
//        initRules();
//    }
//
//    private void initRules() {
//        rules = new ArrayList<>();
//        ParenthesesOpenCloseRule parenthesesRule = new ParenthesesOpenCloseRule();
//        rules.add(parenthesesRule);
//    }
//
//    public String[] getErrorMessages() {
//        String[] result = new String[rules.size()];
//        for (int i = 0; i < rules.size(); i++) {
//            result[i] = rules.get(i).getErrorMessage();
//        }
//        return result;
//    }
}
