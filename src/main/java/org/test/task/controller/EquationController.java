package org.test.task.controller;

import java.util.Objects;
import java.util.Scanner;

public class EquationController implements Controller {
    Boolean isExit = false;
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your Equation here: ");
        String equation = scanner.nextLine();
        System.out.println("Enter your result: ");
        String result = scanner.nextLine();
    }

    public void toggle(Context context) {
        context.setIsExit(isExit);
        context.setController(new FetcherController());
    }
}
