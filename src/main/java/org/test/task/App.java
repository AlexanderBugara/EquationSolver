package org.test.task;

import org.test.task.controller.Context;
import org.test.task.controller.Controller;
import org.test.task.controller.EquationContext;
import org.test.task.controller.EquationController;

/**
 * Hello world!
 *
 */
public class App {

    public static void main( String[] args )
    {
        EquationContext context = new EquationContext();
        context.execute();
    }


}
