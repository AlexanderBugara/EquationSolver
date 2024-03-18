package org.test.task.controller;
import org.test.task.evaluator.Equation;
import org.test.task.persistance.EquationSaver;
import org.test.task.persistance.EquationSaving;

import java.util.Scanner;

public class EquationController implements Controller {

    public EquationController(EquationSaving saver) {

    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        Equation equation = new Equation();
        EquationSaver saver = new EquationSaver();
        while (true) {
            System.out.println("Enter root (press 'q' to quit):");
            System.out.println("Enter your Equation here: ");
            String equationString = scanner.nextLine();
            System.out.println("Enter your roots: ");

            String input = scanner.nextLine(); // Read a line of input
            if (input.isEmpty()) {
                continue; // Skip empty input
            }
            char c = input.charAt(0); // Get the first character
            if (c == 'q') {
                break;
            }
            System.out.print(equationString + " Entered root: " + input);
            if (equation.isEquitationTrue(equationString, Double.valueOf(input))) {
                System.out.println(" is valid");
                saver.save(equationString, Double.valueOf(input));
            } else {
                System.out.println(" is not valid");
            }
        }
    }

    public void toggle(Context context) {
        context.setController(new FetcherController());
    }
}
