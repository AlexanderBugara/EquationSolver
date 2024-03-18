package org.test.task.controller;

public interface Context {
    Controller controller = null;

    Controller getController();

    void setController(Controller controller);

}
