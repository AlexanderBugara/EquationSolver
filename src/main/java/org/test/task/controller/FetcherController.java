package org.test.task.controller;
import org.test.task.persistance.EquationSaver;
import org.test.task.persistance.SelectExprByRoots;
import org.test.task.persistance.SelectExprHasOneRoot;
import org.test.task.persistance.Predicate;

import java.util.Objects;
import java.util.Scanner;
import java.util.HashSet;

public class FetcherController implements Controller {
    @Override
    public void run() {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Please, select the case fetch expressions");
            System.out.println("1. Expressions have only one root.");
            System.out.println("2. Expressions selection by roots.");
            System.out.println("Switch to input new expression: `q`");
            System.out.println("Write 'exit' to exit.");
            String line = scanner.next();

            if (line.isEmpty()) {
                continue; // Skip empty input
            }
            if (Objects.equals(line, "exit")) {
                System.exit(0);
            } else if (line.charAt(0) == 'q') {
                break;
            } else if (line.charAt(0) == '1') {
                allExpressionsForSpecificRoot();
            } else if (line.charAt(0) == '2') {
                expressionsForAnyRoot();
            }
        }
    }

    private void allExpressionsForSpecificRoot() {
        Predicate predicate = new SelectExprHasOneRoot();
        String[] result = predicate.execute();
        if (result.length == 0) {
            System.out.println("Result: expressions did not find;\n");
        }
        for (String expression: result) {
            System.out.println(expression);
        }
    }

    private void expressionsForAnyRoot() {
        Scanner scanner = new Scanner(System.in);
        HashSet<Double> roots = new HashSet<>();
        while (true) {
            System.out.println("Enter root as a Double value");
            System.out.println("Enter f to fetch");

            String line = scanner.next();
            if (!line.isEmpty() && line.charAt(0) == 'f') {
                break;
            } else {
                try {
                    Double root = Double.valueOf(line);
                    roots.add(root);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (!roots.isEmpty()) {
            Double[] array = roots.toArray(new Double[0]);
            SelectExprByRoots byRoots = new SelectExprByRoots(array);
            String[] result = byRoots.execute();
            if (result.length == 0) {
                System.out.println("Result: did not find.");
            } else {
                for (String expression : result) {
                    System.out.println(expression);
                }
            }
        }
    }

    @Override
    public void toggle(Context context) {
        context.setController(new EquationController(new EquationSaver()));
    }
}
