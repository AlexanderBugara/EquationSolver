package org.test.task.controller;

public class EquationContext implements Context {
    Controller controller = null;
    Boolean isExit = false;

    public void execute() {
        controller = new EquationController();
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
