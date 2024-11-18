package com.mohiuddin.onlineticketbookingwebapp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.mohiuddin.onlineticketbookingwebapp.DAO.TicketDAO;
import com.mohiuddin.onlineticketbookingwebapp.factory.DBConnection;
import com.mohiuddin.onlineticketbookingwebapp.model.Ticket;

@WebServlet("/SearchAndBookServlet")
public class SearchAndBookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SearchAndBookServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection connection = DBConnection.getConnection()) {
            TicketDAO ticketDAO = new TicketDAO(connection);
            List<Ticket> tickets = ticketDAO.getAllTickets();  // Fetch all tickets

            // Log to verify data
            System.out.println("Tickets fetched: " + tickets.size());

            // Set the tickets in the request attribute
            request.setAttribute("tickets", tickets);

            // Forward the request to the JSP to display tickets
            request.getRequestDispatcher("searchAndBook.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}