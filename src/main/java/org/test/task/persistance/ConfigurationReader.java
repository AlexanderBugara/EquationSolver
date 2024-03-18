package org.test.task.persistance;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationReader {

    public static String getDbUrl() {
        return "jdbc:mysql://localhost:3306/Equation_new";
    }

    public static String getDbUsername() {
        return "user";
    }

    public static String getDbPassword() {
        return "password";
    }
}
