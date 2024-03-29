package org.test.persistence;
import org.junit.Test;
import org.test.task.persistance.DB;
import org.test.task.persistance.EquationSaver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EquationSaverTest {
    @Test
    public void test() {
        EquationSaver saver = new EquationSaver();
        DB.clean();
        saver.save("1+x=2", 1.0);
        saver.save("1+x=3", 2.5);
        Integer key1 = DB.getPrimaryKeyExpression("1+x=2");
        assertNotNull(key1);
        Integer key2 = DB.getPrimaryKeyExpression("1+x=3");
        assertNotNull(key2);
        DB.clean();
    }
}
