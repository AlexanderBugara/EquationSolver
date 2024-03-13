package org.test.task.preprocessor;

public interface Rule {
    public void next(Character character);
    public boolean isSuccess();
    public String getErrorMessage();
    public void reset();
}
