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

import com.mohiuddin.onlineticketbookingwebapp.DAO.BookingDAO;
import com.mohiuddin.onlineticketbookingwebapp.factory.DBConnection;
import com.mohiuddin.onlineticketbookingwebapp.model.Booking;

@WebServlet("/ViewBookingsServlet")
public class ViewBookingsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ViewBookingsServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = null;
        BookingDAO bookingDAO = null;
        List<Booking> bookings = null;

        try {
            // Get a database connection from your DBConnection utility class
            connection = DBConnection.getConnection();
            
            // Pass the connection to the BookingDAO
            bookingDAO = new BookingDAO(connection);

            // Fetch all bookings
            bookings = bookingDAO.getAllBookings();
            
            // Set the bookings as a request attribute
            request.setAttribute("bookings", bookings);

            // Forward to the searchAndBook.jsp page
            request.getRequestDispatcher("searchAndBook.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database connection error");
        } finally {
            // Close the connection if it's open
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
