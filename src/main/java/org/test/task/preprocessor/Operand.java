package org.test.task.preprocessor;

public class Operand implements Element {
    private final double value;

    public Operand(double value) {
        this.value = value;
    }

    @Override
    public double evaluate() {
        return value;
    }
}