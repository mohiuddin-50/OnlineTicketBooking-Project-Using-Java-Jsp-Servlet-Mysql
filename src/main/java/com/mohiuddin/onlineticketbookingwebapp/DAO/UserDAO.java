package com.mohiuddin.onlineticketbookingwebapp.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean registerUser(String username, String password, String email) throws SQLException {
        String query = "INSERT INTO Users (username, password, email) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, email);
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean validateUser(String username, String password) throws SQLException {
        System.out.println("Validating user: " + username + " with password: " + password);
        String query = "SELECT * FROM Users WHERE username = ? AND password = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                boolean userExists = rs.next();
                System.out.println("User found: " + userExists);
                return userExists;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error executing validateUser query", e);
        }
    }

    public int getUserIdByUsername(String username) throws SQLException {
        String query = "SELECT id FROM Users WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        }
        return -1; // Return -1 if the user is not found
    }
}
