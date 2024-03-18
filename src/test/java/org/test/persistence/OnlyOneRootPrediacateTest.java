package org.test.persistence;
import org.junit.Test;
import org.test.task.persistance.DB;
import org.test.task.persistance.EquationSaver;
import org.test.task.persistance.SelectExprHasOneRoot;

import static org.junit.Assert.*;


public class OnlyOneRootPrediacateTest {
    @Test
    public void test() {
        EquationSaver saver = new EquationSaver();
        DB.clean();
        saver.save("1+x=2", 1.0);
        saver.save("1+x=3", 2.5);
        saver.save("1+x=3", 3.0);

        SelectExprHasOneRoot predicate = new SelectExprHasOneRoot();
        String[] result = predicate.execute();
        assertEquals(result, new String[]{"1+x=2", "1+x=3"});
        DB.clean();
    }
}
