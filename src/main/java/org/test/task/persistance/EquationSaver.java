package org.test.task.persistance;
import com.google.protobuf.Struct;

import java.sql.*;

public class EquationSaver implements EquationSaving {
    @Override
    public void save(String equation, Double root) {
        // Database connection details

        try (Connection connection = DriverManager.getConnection(ConfigurationReader.getDbUrl(), ConfigurationReader.getDbUsername(), ConfigurationReader.getDbPassword())) {
            Integer expressionId = getPrimaryKeyExpression(connection, equation);
            boolean isInsert = false;
            if (expressionId == null) {
                expressionId = insert(equation, connection);
                isInsert = true;
            }

            Integer rootId = getPrimaryKeyRoot(connection, root);
            if (rootId == null) {
                rootId = insertRoot(root, connection);
                isInsert = true;
            }

            if (isInsert) {
                // Insert data into Linking table
                String insertLinkingQuery = "INSERT INTO `Expression-Root` (expression, root) VALUES (?, ?)";
                PreparedStatement linkingStatement = connection.prepareStatement(insertLinkingQuery);
                linkingStatement.setInt(1, expressionId);
                linkingStatement.setInt(2, rootId);
                linkingStatement.executeUpdate();
                System.out.println("Data inserted successfully.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Integer getPrimaryKeyExpression(Connection connection, String expression) {
        try (PreparedStatement checkStatement = connection.prepareStatement("SELECT id FROM Equation_new.Expression WHERE value = ?")) {
            checkStatement.setString(1, expression);
            ResultSet resultSet = checkStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id"); // Retrieve the primary key of the existing record
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Integer insert(String equation, Connection connection) throws SQLException {
        String insertExpressionQuery = "INSERT INTO Equation_new.Expression (value) VALUES (?)";
        PreparedStatement stmt = connection.prepareStatement(insertExpressionQuery,  PreparedStatement.RETURN_GENERATED_KEYS);//new String[]{"value"});
        stmt.setString(1, equation);
        stmt.executeUpdate();

        int expressionId;
        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating expression failed, no ID obtained.");
            }
        }
    }

    private Integer getPrimaryKeyRoot(Connection connection, Double root) {
        try (PreparedStatement checkStatement = connection.prepareStatement("SELECT id FROM Equation_new.Root WHERE value = ?")) {
            checkStatement.setString(1, String.valueOf(root));
            ResultSet resultSet = checkStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id"); // Retrieve the primary key of the existing record
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    private int insertRoot(Double root, Connection connection) throws SQLException {
        String insertRootQuery = "INSERT INTO Equation_new.Root (value) VALUES (?)";
        PreparedStatement stmt = connection.prepareStatement(insertRootQuery, PreparedStatement.RETURN_GENERATED_KEYS);
        stmt.setDouble(1, root);
        stmt.executeUpdate();
        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating root failed, no ID obtained.");
            }
        }
    }
}
