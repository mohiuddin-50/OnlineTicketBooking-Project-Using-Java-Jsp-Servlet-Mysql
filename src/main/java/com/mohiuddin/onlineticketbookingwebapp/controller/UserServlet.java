package com.mohiuddin.onlineticketbookingwebapp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.mohiuddin.onlineticketbookingwebapp.DAO.UserDAO;
import com.mohiuddin.onlineticketbookingwebapp.factory.DBConnection;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        try {
            // Establish database connection
            Connection connection = DBConnection.getConnection();
            if (connection != null) {
                System.out.println("Database connection established successfully.");
            } else {
                System.out.println("Failed to establish database connection.");
            }
            userDAO = new UserDAO(connection); // Initialize DAO with connection
        } catch (SQLException e) {
            // Log and throw error if database connection or DAO initialization fails
            e.printStackTrace();
            throw new ServletException("Failed to initialize UserDAO", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle GET request (e.g., displaying the login page)
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println("Received username: " + username);
        System.out.println("Received password: " + password);

        try {
            boolean isValid = userDAO.validateUser(username, password);
            System.out.println("Validation result for user " + username + ": " + isValid);

            if (isValid) {
            	
                // Get user ID by username and store it in the session
                int userId = userDAO.getUserIdByUsername(username);
                System.out.println("User ID: " + userId);

                // Create session and store both username and user ID
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                session.setAttribute("user_id", userId); // Store user ID in session

                // Redirect to home page after successful login
                response.sendRedirect("home.jsp");
            } else {
                // Redirect to login page with error message
                response.sendRedirect("login.jsp?error=Invalid credentials");
            }
        } catch (SQLException e) {
            // Log and handle any SQLException during user validation
            e.printStackTrace();
            response.sendRedirect("login.jsp?error=Database error");
        } catch (Exception e) {
            // Catch any other unexpected exceptions
            e.printStackTrace();
            response.sendRedirect("login.jsp?error=An unexpected error occurred");
        }
    }
}
