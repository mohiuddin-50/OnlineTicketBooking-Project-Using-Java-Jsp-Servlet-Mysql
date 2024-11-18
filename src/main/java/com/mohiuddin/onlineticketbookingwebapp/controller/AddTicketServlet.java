package com.mohiuddin.onlineticketbookingwebapp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.sql.SQLException;
import java.math.BigDecimal;
import java.util.logging.Logger;

import com.mohiuddin.onlineticketbookingwebapp.DAO.TicketDAO;
import com.mohiuddin.onlineticketbookingwebapp.factory.DBConnection;

@WebServlet("/AddTicketServlet")
public class AddTicketServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(AddTicketServlet.class.getName());

    public AddTicketServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String destination = request.getParameter("destination");
        String travelDateStr = request.getParameter("travel_date");
        String priceStr = request.getParameter("price");

        logger.info("Received request to add ticket with destination: " + destination + ", travel date: " + travelDateStr + ", price: " + priceStr);

        if (destination == null || destination.isEmpty() || travelDateStr == null || priceStr == null || priceStr.isEmpty()) {
            logger.warning("Missing required fields in the request.");
            request.setAttribute("error", "Missing required fields.");
            request.getRequestDispatcher("addTicket.jsp").forward(request, response);
            return;
        }

        double priceDouble;
        try {
            priceDouble = Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            logger.warning("Invalid price format: " + priceStr);
            request.setAttribute("error", "Invalid price format.");
            request.getRequestDispatcher("addTicket.jsp").forward(request, response);
            return;
        }

        BigDecimal price = BigDecimal.valueOf(priceDouble);
        Date travelDate = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = sdf.parse(travelDateStr);
            travelDate = new Date(parsedDate.getTime());
        } catch (Exception e) {
            logger.warning("Invalid date format: " + travelDateStr);
            request.setAttribute("error", "Invalid date format.");
            request.getRequestDispatcher("addTicket.jsp").forward(request, response);
            return;
        }

        try (Connection connection = DBConnection.getConnection()) {
            TicketDAO ticketDAO = new TicketDAO(connection);
            boolean isAdded = ticketDAO.addTicket(destination, travelDate, price);

            if (isAdded) {
                logger.info("Ticket successfully added to the database.");
                response.sendRedirect("ViewTicketsServlet");
            } else {
                logger.severe("Unable to add ticket to the database.");
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to add ticket.");
            }
        } catch (SQLException e) {
            logger.severe("Database error occurred: " + e.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error.");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("adminLogin.jsp");
    }
}
