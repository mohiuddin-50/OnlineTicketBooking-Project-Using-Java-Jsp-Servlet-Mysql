package com.mohiuddin.onlineticketbookingwebapp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class AdminLogoutServlet
 */
@WebServlet("/AdminLogoutServlet")
public class AdminLogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	/*
	 * protected void doGet(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { // Invalidate the session if
	 * it exists HttpSession session = request.getSession(false); // Don't create a
	 * new session if (session != null) { session.invalidate(); // Invalidate the
	 * session to log out }
	 * 
	 * // Redirect to the login page response.sendRedirect("adminLogin.jsp"); //
	 * Redirect to login page }
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 HttpSession session = request.getSession(false);
    	 // Invalidate the session to log the user out
        session.invalidate();
        // Redirect the user to the login page after logout
        response.sendRedirect("adminLogin.jsp");
    }
//        HttpSession session = request.getSession(false); // Get session if exists
//        if (session == null || session.getAttribute("admin") == null) {
//            // Not logged in, redirect to login page
//            response.sendRedirect("adminLogin.jsp");
//            return;
//        }
//
//        // If logged in, proceed with the dashboard logic
//        request.getRequestDispatcher("adminDashboard.jsp").forward(request, response);
//    }
    

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
