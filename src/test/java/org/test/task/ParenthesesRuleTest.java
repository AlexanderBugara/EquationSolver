package org.test.task;

import junit.framework.TestCase;
import org.test.task.preprocessor.ParenthesesOpenCloseRule;

public class ParenthesesRuleTest extends TestCase {
    private ParenthesesOpenCloseRule parenthesesRule;
    public void setUp() throws Exception {
        super.setUp();
        parenthesesRule = new ParenthesesOpenCloseRule();
    }

    public class Expectation<A, B> {
        private final A first;
        private final B second;

        public Expectation(A first, B second) {
            this.first = first;
            this.second = second;
        }

        public A getFirst() {
            return first;
        }

        public B getSecond() {
            return second;
        }

    }


    public void testInput() {
        Expectation<Boolean, String> expectation1 = new Expectation<>(true, "2+3(4-1)*x=233");
        Expectation<Boolean, String> expectation2 = new Expectation<>(false, "2+(3((4-1))*x=)233");
        Expectation<Boolean, String> expectation3 = new Expectation<>(false, "(2+3(4-1)*x=23)3");
        Expectation<Boolean, String> expectation4 = new Expectation<>(false, "(2+3((4-1)*x=23)3");
        Expectation<Boolean, String> expectation5 = new Expectation<>(false, "(2+3(4-1)))*x=23)3");
        Expectation<Boolean, String> expectation6 = new Expectation<>(false, "(((2+3(4-1)*x=23)3");
        Expectation<Boolean, String> expectation7 = new Expectation<>(true, "2+34-1*x=233");

        Expectation[] expectations = new Expectation[]{expectation1,
                expectation2, expectation3, expectation4, expectation5, expectation6, expectation7};

        for (int i = 0; i < expectations.length; i++) {
           Expectation expectation = expectations[i];
           boolean result = validate((String) expectation.second);
           String errorMessage = parenthesesRule.getErrorMessage();
           assertEquals(errorMessage + " index is: " + i, expectation.first, result);

           if (!parenthesesRule.isSuccess()) {
               assertNotNull(errorMessage);
           } else {
               assertNull(errorMessage);
           }
            parenthesesRule.reset();
        }
    }

    private boolean validate(String equation) {
        for (char character : equation.toCharArray()) {
            parenthesesRule.next(character);
        }
        return parenthesesRule.isSuccess();
    }

}
