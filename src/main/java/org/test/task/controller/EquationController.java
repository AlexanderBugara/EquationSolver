package org.test.task.controller;
import org.test.task.evaluator.Equation;
import org.test.task.persistance.EquationSaving;

import java.util.Scanner;

public class EquationController implements Controller {
    Boolean isExit = false;


    public EquationController(EquationSaving saver) {

    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your Equation here: ");
        String equationString = scanner.nextLine();
        System.out.println("Enter your roots: ");

        System.out.println("Enter root (press 'q' to quit):");
        // Read characters until 'q' is pressed
        Equation equation = new Equation();

        while (true) {
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
            } else {
                System.out.println(" is not valid");
            }
        }
        scanner.close();
    }

    public void toggle(Context context) {
        context.setIsExit(isExit);
        context.setController(new FetcherController());
    }
}
