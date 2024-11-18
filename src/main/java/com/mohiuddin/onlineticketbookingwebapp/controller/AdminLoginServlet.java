package com.mohiuddin.onlineticketbookingwebapp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

import com.mohiuddin.onlineticketbookingwebapp.DAO.AdminDAO;

@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AdminLoginServlet() {
        super();
    }

    // Handling POST request for login
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        AdminDAO adminDAO = new AdminDAO();
        boolean isAuthenticated = adminDAO.authenticateAdmin(username, password);

        if (isAuthenticated) {
            // Create a new session and set user info
            HttpSession session = request.getSession(true);
            session.setAttribute("admin", username);  // Store username or admin object in session
            
            // Redirect to the admin dashboard page
            response.sendRedirect("adminDashboard.jsp");
        } else {
            // Invalid login, redirect to login page with an error message
            response.sendRedirect("adminLogin.jsp?error=invalid");
        }
    	
    }
//    	String username = request.getParameter("username");
//        String password = request.getParameter("password");
//
//        // Dummy authentication logic
//        if ("admin".equals(username) && "123".equals(password)) {
//            // Creating a session
//            HttpSession session = request.getSession();
//            
//            // Storing attributes in the session
//            session.setAttribute("username", username);
//            session.setAttribute("admin", true); // Flag to indicate admin access
//            
//            // Redirect to a dashboard
//            response.sendRedirect("adminDashboard.jsp");
//        } else {
//            // Redirect to login page on failure
//            response.sendRedirect("adminLogin.jsp?error=invalid");
//        }
//    }
    

    // Handling GET request for login page or redirection if already logged in
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Get the existing session if available
        if (session != null && session.getAttribute("admin") != null) {
            // If already logged in, redirect to admin dashboard page
            response.sendRedirect("adminDashboard.jsp");
        } else {
            // If not logged in, forward the request to login page
            request.getRequestDispatcher("adminLogin.jsp").forward(request, response);
        }
    }
}
