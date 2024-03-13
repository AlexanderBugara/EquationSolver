package org.test.task.controller;

public interface Context {
    Controller controller = null;
    Boolean isExit = false;

    Controller getController();

    void setController(Controller controller);

    Boolean getIsExit();

    void setIsExit(Boolean isExit);

}
