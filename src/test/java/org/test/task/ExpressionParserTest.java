package org.test.task;

import junit.framework.TestCase;
import org.test.task.preprocessor.ExpressionParser;

public class ExpressionParserTest extends TestCase {
    private ExpressionParser parser;
    public void setUp() throws Exception {
        super.setUp();
        //"-1 * 2 * (3 + ((1+1)) * 3 + 4 / 2 *4) * 0.5 * 1) * -1"
        //"2 * (3 + ((1+1)) * 3 + 4 / 2 *4) * - 1 * 0.5 * 1) * -1"
        //"2 * (-1 * 3 + ((1+1)) * 3 + 4 / 2 *4) * - 1 * 0.5 * 1)"
        //"2 * (3 + ((1+1)) * 3 + 4 / 2 *4) * - 1 * 0.5 * 1 * -1)"
        parser = new ExpressionParser("2 * (3 + ((1+1)) * 3 + 4 / 2 *4) * 0.5 * 1) * -1");
        //""2 * (3 + ((1+1)) * 3 + 4 / 2 *4) * 0.5");
    }

    public void testInput() {
        double result = parser.parse().evaluate();
        assertEquals(17.0, result);
    }
}
