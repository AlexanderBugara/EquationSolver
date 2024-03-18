package org.test.task.controller;

import org.test.task.persistance.EquationSaver;

import java.util.Scanner;

public class EquationContext implements Context {
    Controller controller = null;

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Do you want to read or write?");
            System.out.println("1. Read");
            System.out.println("2. Write");
            System.out.println("q. to terminate");
            String line = scanner.next();
            if (!line.isEmpty() && line.charAt(0) == 'q') {
                System.exit(0);
            } else if (!line.isEmpty() && line.charAt(0) == '1') {
                controller = new FetcherController();
                controller.run();
                controller.toggle(this);
            } else if (!line.isEmpty() && line.charAt(0) == '2') {
                controller = new EquationController(new EquationSaver());
                controller.run();
                controller.toggle(this);
            }
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
}
