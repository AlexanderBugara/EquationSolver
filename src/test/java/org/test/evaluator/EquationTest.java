package org.test.evaluator;
import org.junit.Test;
import org.test.task.evaluator.Equation;
import static org.junit.Assert.*;

import static org.junit.Assert.assertEquals;

public class EquationTest {

    @Test
    public void testEquals() {
        Equation equation = new Equation();
        boolean result = equation.isEquitationTrue("2*x+5=17", 6.0);
        assertTrue(result);
    }

    @Test
    public void testEquals1() {
        double v = -65.0 / 12.0;
        Equation equation = new Equation();
        boolean result = equation.isEquitationTrue("-1.3*5/x=1.2", v);
        assertTrue(result);
    }

    @Test
    public void testEquals2() {
        double root1 = Math.sqrt(5);
        double root2 = -Math.sqrt(5);
        Equation equation = new Equation();
        boolean result = equation.isEquitationTrue("2*x*x=10", root1);
        assertTrue(result);
        result = equation.isEquitationTrue("2*x*x=10", root2);
        assertTrue(result);
    }

    @Test
    public void testEquals3() {
        double v = -5.0/4.0;
        Equation equation = new Equation();
        boolean result = equation.isEquitationTrue("2*(x+5+x)+5=10", v);
        assertTrue(result);
    }

    @Test
    public void testEquals4() {
        Equation equation = new Equation();
        boolean result = equation.isEquitationTrue("17=2*x+5", 6.0);
        assertTrue(result);
    }
}