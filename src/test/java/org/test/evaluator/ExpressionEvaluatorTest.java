package org.test.evaluator;
import org.junit.Test;
import org.test.task.evaluator.ExpressionEvaluator;
import org.test.task.evaluator.Variable;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExpressionEvaluatorTest {
    @Test
    public void testX() {
        String expression = "2*x+5";
        ExpressionEvaluator eval = new ExpressionEvaluator(null);
        Variable variable = new Variable(4.0);
        double reult = eval.evaluate(expression, variable);
        assertEquals(reult, 13.0, 0.0001);
    }

    @Test
    public void testDivisionByZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            ExpressionEvaluator eval = new ExpressionEvaluator(null);
            eval.evaluate("1/0", null);
        });
    }

    @Test
    public void testMultipleOperations() {
        assertThrows(IllegalArgumentException.class, () -> {
            ExpressionEvaluator eval = new ExpressionEvaluator(null);
            eval.evaluate("3+*4", null);
        });
    }
    @Test
    public void testOperationWithNegativeValue() {
        ExpressionEvaluator eval = new ExpressionEvaluator(null);
        double relult = eval.evaluate("4*-7", null);
        assertEquals(-28, relult, 0.0001);
    }

    //2*x+5=17, -1.3*5/x=1.2, 2*x*x=10, 2*(x+5+х)+5=10, 17=2*x+5
}
