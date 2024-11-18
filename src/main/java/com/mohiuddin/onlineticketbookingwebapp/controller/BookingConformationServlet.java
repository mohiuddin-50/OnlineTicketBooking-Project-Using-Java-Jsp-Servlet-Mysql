package com.mohiuddin.onlineticketbookingwebapp.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.mohiuddin.onlineticketbookingwebapp.DAO.BookingDAO;
import com.mohiuddin.onlineticketbookingwebapp.DAO.TicketDAO;
import com.mohiuddin.onlineticketbookingwebapp.factory.DBConnection;
import com.mohiuddin.onlineticketbookingwebapp.model.Booking;

@WebServlet("/BookingConformationServlet")
public class BookingConformationServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private BookingDAO bookingDAO;
    @SuppressWarnings("unused")
	private TicketDAO ticketDAO;

    @Override
    public void init() {
        try {
            bookingDAO = new BookingDAO(DBConnection.getConnection());
            ticketDAO = new TicketDAO(DBConnection.getConnection());
        } catch (SQLException e) {
            throw new RuntimeException("Error initializing DAO connections", e);
        }
    }

    public BookingConformationServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        String ticketIdParam = request.getParameter("ticketId");
        System.out.println("Ticket ID received: " + ticketIdParam);

        if (ticketIdParam == null || ticketIdParam.isEmpty()) {
            request.setAttribute("errorMessage", "Ticket ID is missing. Please try again.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("searchAndBook.jsp");
            dispatcher.forward(request, response);
            return;
        }

        try {
            int ticketId = Integer.parseInt(ticketIdParam);

            HttpSession session = request.getSession();
            Integer userId = (Integer) session.getAttribute("user_id");  // Retrieve user_id from session
            System.out.println("User ID from session: " + userId);

            if (userId == null) {
                request.setAttribute("errorMessage", "You must be logged in to make a booking.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                dispatcher.forward(request, response);
                return;
            }

            boolean isBooked = bookingDAO.createBooking(userId, ticketId);
            System.out.println("Booking creation result: " + isBooked);

            if (isBooked) {
                int bookingId = bookingDAO.getLatestBookingId(userId, ticketId);
                request.setAttribute("bookingId", bookingId);
                RequestDispatcher dispatcher = request.getRequestDispatcher("bookingConformation.jsp");
                dispatcher.forward(request, response);
            } else {
                request.setAttribute("errorMessage", "Booking failed. Please try again.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("searchAndBook.jsp");
                dispatcher.forward(request, response);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Ticket ID format.");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error occurred while booking.");
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Booking> bookings = bookingDAO.getAllBookings();
            request.setAttribute("bookings", bookings);
            RequestDispatcher dispatcher = request.getRequestDispatcher("bookingHistory.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error occurred while fetching booking history.");
        }
    }
}
