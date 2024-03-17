package org.test.task.controller;

import org.test.task.persistance.EquationSaver;

public class EquationContext implements Context {
    Controller controller = null;
    Boolean isExit = false;

    public void execute() {
        controller = new EquationController(new EquationSaver());
        while (!isExit) {
            controller.run();
            controller.toggle(this);
        }
    }

    @Override
    public Controller getController() {
        return controller;
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void setIsExit(Boolean isExit) {
        this.isExit = isExit;
    }

    @Override
    public Boolean getIsExit() {
        return isExit;
    }
}
