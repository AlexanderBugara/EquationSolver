package org.test.task.controller;
import org.test.task.persistance.EquationSaver;

import java.util.Scanner;

public class FetcherController implements Controller {
    @Override
    public void run() {
        System.out.println("FetcherController");
        Scanner scanner = new Scanner(System.in);
        String result = scanner.nextLine();
    }

    @Override
    public void toggle(Context context) {
        context.setController(new EquationController(new EquationSaver()));
    }
}
