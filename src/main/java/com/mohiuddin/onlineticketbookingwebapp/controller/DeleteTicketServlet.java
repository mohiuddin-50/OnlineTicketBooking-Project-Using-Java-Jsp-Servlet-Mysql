package com.mohiuddin.onlineticketbookingwebapp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.mohiuddin.onlineticketbookingwebapp.DAO.TicketDAO;
import com.mohiuddin.onlineticketbookingwebapp.factory.DBConnection;

/**
 * Servlet implementation class DeleteTicketServlet
 */
@WebServlet("/DeleteTicketServlet") 
public class DeleteTicketServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeleteTicketServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        String ticketIdStr = request.getParameter("ticketId");

        if (ticketIdStr != null && !ticketIdStr.isEmpty()) {
            try {
                int ticketId = Integer.parseInt(ticketIdStr); // Parse ticket ID
                try (Connection connection = DBConnection.getConnection()) {
                    TicketDAO ticketDAO = new TicketDAO(connection);

                    // Delete ticket using the DAO
                    boolean isDeleted = ticketDAO.deleteTicket(ticketId);

                    if (isDeleted) {
                        response.sendRedirect("ViewTicketsServlet"); // Redirect to ViewTicketsServlet after successful deletion
                    } else {
                        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to delete ticket.");
                    }
                } catch (SQLException e) {
                    log("Database error during ticket deletion", e);
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error.");
                }
            } catch (NumberFormatException e) {
                log("Invalid ticket ID format", e);
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid ticket ID format.");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Ticket ID is required.");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response); // Call doGet for handling both GET and POST requests
    }

    private void log(String message, Exception e) {
        getServletContext().log(message, e); // Use the servlet context to log messages
    }
}
