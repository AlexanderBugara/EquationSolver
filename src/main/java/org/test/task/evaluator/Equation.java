package org.test.task.evaluator;

public class Equation {
    public boolean isEquitationTrue(String equitation, Double variable) {
        String[] separated = equitation.split("=");
        if (separated.length != 2) {
            throw new IllegalArgumentException("Error with '=' sign");
        }
        String left = separated[0];
        String right = separated[1];

        ExpressionEvaluator evaluator = new ExpressionEvaluator(null);
        Variable var = new Variable(variable);
        Double resultLeft = evaluator.evaluate(left, var);
        Double resultRight = evaluator.evaluate(right, var);
        double maxDelta = 1.0E-9;

        if ((resultLeft <= 0 && resultRight <= 0) ||
                (resultLeft >= 0 && resultRight >= 0)) {
            return Math.abs(resultLeft - resultRight) < maxDelta;
        }
        return false;
    }
}
