package org.test.task.persistance;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class EquationSaver implements EquationSaving {

    @Override
    public void save(String equation, Double root) {
        // Database connection parameters
        String url = "jdbc:mysql://localhost:3306/Equation_new";
        String user = "springstudent";
        String password = "springstudent";

        // SQL query to insert data
        String sql = "INSERT INTO Equation_new.Expression" + "(value)" + "VALUES(?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            // Set parameters
            stmt.setString(1, equation);
            // Execute the query
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new row was inserted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
