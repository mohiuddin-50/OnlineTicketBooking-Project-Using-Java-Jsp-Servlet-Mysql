package com.mohiuddin.onlineticketbookingwebapp.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.mohiuddin.onlineticketbookingwebapp.DAO.BookingDAO;
import com.mohiuddin.onlineticketbookingwebapp.factory.DBConnection;
import com.mohiuddin.onlineticketbookingwebapp.model.Booking;

@WebServlet("/BookingHistoryServlet")
public class BookingHistoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private BookingDAO bookingDAO;

    @Override
    public void init() throws ServletException {
        try {
            bookingDAO = new BookingDAO(DBConnection.getConnection());
        } catch (SQLException e) {
            throw new ServletException("Database connection error.", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Booking> bookings = bookingDAO.getAllBookings();
            request.setAttribute("bookings", bookings);
            RequestDispatcher dispatcher = request.getRequestDispatcher("bookingHistory.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
                    "Error occurred while fetching booking history.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int userId = Integer.parseInt(request.getParameter("userId"));
            int ticketId = Integer.parseInt(request.getParameter("ticketId"));

            // Create a new booking (no need for paymentStatus or quantity)
            boolean isBooked = bookingDAO.createBooking(userId, ticketId);

            if (isBooked) {
                doGet(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Booking failed.");
            }
        } catch (SQLException | NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
                    "Error occurred while processing the booking.");
        }
    }
}
