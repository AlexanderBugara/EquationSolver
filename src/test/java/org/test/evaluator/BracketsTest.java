package org.test.evaluator;
import org.junit.Test;
import org.test.task.evaluator.ExpressionEvaluator;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BracketsTest {
    @Test
    public void testEquals() {
        ExpressionEvaluator eval = new ExpressionEvaluator(null);
        assertEquals(1.0, eval.evaluate("(0.5)+(0.5)", null), 0.0001);
    }

    @Test
    public void testUnclosedBrackets() {
        assertThrows(IllegalArgumentException.class, () -> {
            ExpressionEvaluator eval = new ExpressionEvaluator(null);
            assertEquals(1.0, eval.evaluate("(0.5)+(0.5", null), 0.0001);
        });
    }

    @Test
    public void testUnopenedBrackets() {
        assertThrows(IllegalArgumentException.class, () -> {
            ExpressionEvaluator eval = new ExpressionEvaluator(null);
            assertEquals(1.0, eval.evaluate("(0.5)+0.5)", null), 0.0001);
        });
    }

    @Test
    public void testNestedBrackets() {
        ExpressionEvaluator eval = new ExpressionEvaluator(null);
        assertEquals(8.0, eval.evaluate("(0.5)+((((6+1))))+(0.5)", null), 0.0001);
    }
}
