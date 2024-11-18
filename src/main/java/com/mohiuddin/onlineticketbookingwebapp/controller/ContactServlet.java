package com.mohiuddin.onlineticketbookingwebapp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mohiuddin.onlineticketbookingwebapp.factory.DBConnection;

@WebServlet("/ContactServlet")
public class ContactServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ContactServlet() {
        super();
    }

    /**
     *
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Placeholder method, in case you want to handle GET requests
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieving form data
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String message = request.getParameter("message");

        // Use DBConnection to get a database connection
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO contacts (name, email, message) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, name);
                stmt.setString(2, email);
                stmt.setString(3, message);

                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    response.sendRedirect("contactSuccess.jsp"); // Redirect to success page after storing
                } else {
                    response.sendRedirect("contact.jsp?error=true"); // Redirect with error message if insertion fails
                }
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendRedirect("contact.jsp?error=true"); // Redirect with error if SQL exception occurs
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("contact.jsp?error=true"); // Redirect with error if DB connection fails
        }
    }
}
