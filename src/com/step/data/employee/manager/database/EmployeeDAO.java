package com.step.data.employee.manager.database;

import com.step.data.employee.Employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// data access obj (dao)
public class EmployeeDAO {
    private static final String dbTable = "employee_manager.employee";

    public Connection initConnection() throws SQLException {
        String url = "jdbc:postgresql://127.0.0.1:5432/employee_manager";
        String username = "postgres";
        String password = "admin";

        return DriverManager.getConnection(url, username, password);
    }

    public void insert(Employee e) {
        String sql = " INSERT INTO " + dbTable +
                " (name, surname, idnp, birthdate, salary, job) " +
                " values(?,?,?,?,?,?) ";

        try (Connection connection = initConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            // set ?s
            statement.setString(1, e.getName());
            statement.setString(2, e.getSurname());
            statement.setString(3, String.valueOf(e.getIdnp()));
            statement.setString(4, String.valueOf(e.getBirthDate()));
            statement.setDouble(5, e.getSalary());
            statement.setString(6, String.valueOf(e.getJob()));
            // execute statement
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
