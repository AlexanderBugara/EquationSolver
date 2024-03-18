package org.test.task.persistance;

import java.sql.*;
import java.util.LinkedList;

public class SelectExprHasOneRoot implements Predicate {
    @Override
    public String[] execute() {
        try(Connection connection = DriverManager.getConnection(ConfigurationReader.getDbUrl(), ConfigurationReader.getDbUsername(), ConfigurationReader.getDbPassword())) {
            String sql = "SELECT DISTINCT e.value AS expression_value\n" +
                    "FROM Expression e\n" +
                    "JOIN `Expression-Root` er ON e.id = er.expression;\n";
            LinkedList<String> result = new LinkedList<>();

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {
                    String expression = resultSet.getString("expression_value");
                    System.out.println("Expression Value: " + expression);
                    result.add(expression);
                }
                return result.toArray(new String[0]);
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }
        return new String[0];
    }
}
