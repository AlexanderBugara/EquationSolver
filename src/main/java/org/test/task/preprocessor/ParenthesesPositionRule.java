package org.test.task.preprocessor;

import java.util.ArrayList;

public class ParenthesesPositionRule implements Rule {
    private char previousCharacter = '\u0000';
    private int charIndex = 0;
    ArrayList<String> errorIndexes = new ArrayList<>();

    private boolean isError = false;
//    Expectation<Boolean, String> expectation1 = new Expectation<>(true, "2+3(4-1)*x=233");
//    Expectation<Boolean, String> expectation2 = new Expectation<>(false, "2+(3((4-1))*x=)233");
//    Expectation<Boolean, String> expectation3 = new Expectation<>(false, "(2+3(4-1)*x=23)3");
//    Expectation<Boolean, String> expectation4 = new Expectation<>(false, "(2+3((4-1)*x=23)3");
//    Expectation<Boolean, String> expectation5 = new Expectation<>(false, "(2+3(4-1)))*x=23)3");
//    Expectation<Boolean, String> expectation6 = new Expectation<>(false, "(((2+3(4-1)*x=23)3");
//    Expectation<Boolean, String> expectation7 = new Expectation<>(false, "2+(4-1*x=233)");

    @Override
    public void next(Character ch) {
        if ((ch == '(') && (ch != '\u0000' && !isOperator(previousCharacter))) {
            errorIndexes.add(String.valueOf(charIndex));
            isError = true;
        } else if (ch == '=') {
            previousCharacter = '\u0000';
        } else if (ch != '=') {
            previousCharacter = ch;
        }
        charIndex++;
    }


    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    @Override
    public boolean isSuccess() {
        return !isError;
    }

    @Override
    public String getErrorMessage() {
        String result = null;
        if (isError) {
            String divider = " | ";
            result = "Error: parentheses positions is wrong: " + String.join(divider, errorIndexes);
        }
        return result;
    }

    @Override
    public void reset() {
       previousCharacter = '\u0000';
       isError = false;
    }
}
