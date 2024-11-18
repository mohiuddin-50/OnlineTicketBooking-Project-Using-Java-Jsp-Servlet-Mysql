package com.mohiuddin.onlineticketbookingwebapp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import com.mohiuddin.onlineticketbookingwebapp.DAO.TicketDAO;
import com.mohiuddin.onlineticketbookingwebapp.factory.DBConnection;
import com.mohiuddin.onlineticketbookingwebapp.model.Ticket;

@WebServlet("/EditTicketServlet")
public class EditTicketServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditTicketServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!isAuthenticated(request)) {
            response.sendRedirect("adminLogin.jsp"); // Redirect to login if not authenticated
            return;
        }

        String ticketIdStr = request.getParameter("ticketId");

        if (ticketIdStr != null && !ticketIdStr.isEmpty()) {
            try {
                int ticketId = Integer.parseInt(ticketIdStr);
                try (Connection connection = DBConnection.getConnection()) {
                    TicketDAO ticketDAO = new TicketDAO(connection);
                    Ticket ticket = ticketDAO.getTicketById(ticketId);
                    if (ticket != null) {
                        request.setAttribute("ticket", ticket);
                        request.getRequestDispatcher("editTicket.jsp").forward(request, response);
                    } else {
                        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Ticket not found.");
                    }
                } catch (SQLException e) {
                    log("Database error during ticket retrieval", e); 
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!isAuthenticated(request)) {
            response.sendRedirect("adminLogin.jsp"); // Redirect to login if not authenticated
            return;
        }

        String ticketIdStr = request.getParameter("ticket_id");
        String destination = request.getParameter("destination");
        String travelDateStr = request.getParameter("travel_date");
        String priceStr = request.getParameter("price");

        if (ticketIdStr == null || ticketIdStr.isEmpty() || destination == null || destination.isEmpty() || travelDateStr == null || travelDateStr.isEmpty() || priceStr == null || priceStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "All fields must be filled.");
            return;
        }

        int ticketId;
        double priceDouble;
        Date travelDate = null;

        try {
            ticketId = Integer.parseInt(ticketIdStr);
            priceDouble = Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid number format.");
            return;
        }

        BigDecimal price = BigDecimal.valueOf(priceDouble);

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = sdf.parse(travelDateStr);
            travelDate = new Date(parsedDate.getTime());
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid date format.");
            return;
        }

        try (Connection connection = DBConnection.getConnection()) {
            TicketDAO ticketDAO = new TicketDAO(connection);
            boolean isUpdated = ticketDAO.updateTicket(ticketId, destination, travelDate, price);

            if (isUpdated) {
                response.sendRedirect("ViewTicketsServlet");
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to update ticket.");
            }
        } catch (SQLException e) {
            log("Database error during ticket update", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error.");
        }
    }
    private boolean isAuthenticated(HttpServletRequest request) {
        HttpSession session = request.getSession(false); // Get existing session (don't create a new one)
        return session != null && session.getAttribute("admin") != null; // Check if 'admin' attribute exists in session
    }


    // Helper method to log errors
    private void log(String message, Exception e) {
        // Implement your logging mechanism here, such as SLF4J or java.util.logging
        getServletContext().log(message, e);
    }
}
