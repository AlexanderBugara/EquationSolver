package org.test.task.persistance;

import java.sql.*;
import java.util.LinkedList;

public class SelectExprByRoots implements Predicate {
    private Double[] roots;
    public SelectExprByRoots(Double[] roots) {
        this.roots = roots;
    }

    @Override
    public String[] execute() {
        try(
                Connection connection = DriverManager.getConnection(ConfigurationReader.getDbUrl(), ConfigurationReader.getDbUsername(), ConfigurationReader.getDbPassword()))

        {
            StringBuilder rootValuesStr = new StringBuilder("(");
            for (int i = 0; i < roots.length; i++) {
                System.out.println("Root value " + (i + 1) + ": ");
                rootValuesStr.append(roots[i]);
                if (i < roots.length - 1) {
                    rootValuesStr.append(", ");
                }
            }
            rootValuesStr.append(")");

            String sql = "SELECT r.value AS root_value, e.value AS expression_value " +
                    "FROM Root r " +
                    "JOIN `Expression-Root` er ON r.id = er.root " +
                    "JOIN Expression e ON er.expression = e.id " +
                    "WHERE r.value IN " + rootValuesStr;
            LinkedList<String> result = new LinkedList<>();

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {
                    double rootValue = resultSet.getDouble("root_value");
                    String expressionValue = resultSet.getString("expression_value");
                    System.out.println("Root Value: " + rootValue + ", Expression Value: " + expressionValue);
                    result.add(expressionValue);
                }
                return result.toArray(new String[0]);
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }
        return new String[0];
    }

}
