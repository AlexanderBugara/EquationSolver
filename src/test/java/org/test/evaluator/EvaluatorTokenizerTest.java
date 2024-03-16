package org.test.evaluator;
import org.junit.Test;
import org.test.task.evaluator.Tokenizer;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;

public class EvaluatorTokenizerTest {
    @Test
    public void test() {
        Tokenizer tokenizer = new Tokenizer();
        String expression = "x+(3.2/2)";
        String[] expected = {"x", "+", "(", "3.2", "/", "2", ")"};
        final Iterator<String> tokens = tokenizer.tokenize(expression, null);
        int index = 0;
        while (tokens.hasNext()) {
            String strToken = tokens.next();
            assertEquals(expected[index], strToken);
            index++;
        }
        assertEquals(index, 7);
    }
}