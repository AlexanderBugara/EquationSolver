package org.test.task.persistance;
import java.sql.*;
public class DB {
    public static  void clean() {
        try (Connection connection = DriverManager.getConnection(ConfigurationReader.getDbUrl(), ConfigurationReader.getDbUsername(), ConfigurationReader.getDbPassword())) {
            String deleteSql1 = "DELETE FROM Equation_new.`Expression-Root`";
            String deleteSql2 = "DELETE FROM Equation_new.Root";
            String deleteSql3 = "DELETE FROM Equation_new.Expression";

            String[] sql_list = {deleteSql1, deleteSql2, deleteSql3};
            for (String sql : sql_list) {
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    int rowsAffected = statement.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("deleted successfully");
                    } else {
                        System.out.println("Not deleted");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Integer getPrimaryKeyExpression(String expression) {
        try (Connection connection = DriverManager.getConnection(ConfigurationReader.getDbUrl(), ConfigurationReader.getDbUsername(), ConfigurationReader.getDbPassword())) {
            try (PreparedStatement checkStatement = connection.prepareStatement("SELECT id FROM Equation_new.Expression WHERE value = ?")) {
                checkStatement.setString(1, expression);
                ResultSet resultSet = checkStatement.executeQuery();
                if (resultSet.next()) {
                    return resultSet.getInt("id"); // Retrieve the primary key of the existing record
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
