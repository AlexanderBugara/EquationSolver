package org.test.task.preprocessor;

public class ParenthesesOpenCloseRule implements Rule {
    private int balanceFirstPart = 0;
    private int balanceSecondPart = 0;
    private boolean isFirstPart = true;


    @Override
    public void next(Character character) {
        if (isFirstPart && character == '(') {
            balanceFirstPart++;
        } else if (isFirstPart && character == ')') {
            balanceFirstPart--;
        } else if (!isFirstPart && character == '(') {
            balanceSecondPart++;
        } else if (!isFirstPart && character == ')') {
            balanceSecondPart--;
        } else if (character == '=') {
            isFirstPart = false;
        }
    }

    @Override
    public void reset() {
        isFirstPart = true;
        balanceFirstPart = 0;
        balanceSecondPart = 0;
    }

    @Override
    public boolean isSuccess() {
        return balanceFirstPart == 0 && balanceSecondPart == 0 && !isFirstPart;
    }

    @Override
    public String getErrorMessage() {
        String result = null;
        if (balanceFirstPart != 0 && balanceSecondPart != 0) {
            result = "Error: Amount open is not equal closed parentheses";
        } else if (balanceFirstPart != 0) {
            result = "Error: First part. Amount open is not equal closed parentheses";
        } else if (balanceSecondPart != 0) {
            result = "Error: Second part. Amount open is not equal closed parentheses";
        }
        return result;
    }
}
