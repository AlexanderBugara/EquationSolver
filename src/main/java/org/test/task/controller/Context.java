package org.test.task.controller;

public interface Context {
    Controller controller = null;
    Boolean isExit = false;

    public Controller getController();

    public void setController(Controller controller);

    public Boolean getIsExit();

    public void setIsExit(Boolean isExit);

}
