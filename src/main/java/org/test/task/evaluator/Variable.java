package org.test.task.evaluator;

public class Variable {
    private Double value;
    public Double get() {
        return value;
    }

    public String name() {
        return "x";
    }

    public Variable(Double value) {
        this.value = value;
    }
}
