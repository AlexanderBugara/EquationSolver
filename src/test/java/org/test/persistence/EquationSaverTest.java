package org.test.persistence;
import org.junit.Test;
import org.test.task.persistance.EquationSaver;

public class EquationSaverTest {
    @Test
    public void test() {
        EquationSaver saver = new EquationSaver();
        saver.save("", 1.0);
    }
}
