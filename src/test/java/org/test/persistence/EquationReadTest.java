package org.test.persistence;

import org.junit.Test;
import org.test.task.persistance.DB;
import org.test.task.persistance.EquationSaver;
import org.test.task.persistance.SelectExprByRoots;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EquationReadTest {
    @Test
    public void test() {
        DB.clean();
        EquationSaver saver = new EquationSaver();
        saver.save("1+x=2", 1.0);
        saver.save("1+x=3", 2.5);
        SelectExprByRoots reader = new SelectExprByRoots(new Double[]{1.0, 2.5, 3.0});
        String[] result = reader.execute();
        assertEquals(result, new String[]{"1+x=2", "1+x=3"});
        DB.clean();
    }
}
