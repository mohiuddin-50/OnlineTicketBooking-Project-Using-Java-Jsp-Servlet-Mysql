
package com.mohiuddin.onlineticketbookingwebapp.DAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.mohiuddin.onlineticketbookingwebapp.model.Booking;

public class BookingDAO {
    private Connection connection;

    public BookingDAO(Connection connection) {
        this.connection = connection;
    }

    // Insert new booking into the database
    public boolean createBooking(int userId, int ticketId) throws SQLException {
        String query = "INSERT INTO Bookings (user_id, ticket_id, booking_date) VALUES (?, ?, CURRENT_TIMESTAMP)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, ticketId);
            return stmt.executeUpdate() > 0;
        }
    }
 // Update createBooking method to match the new constructor
    public boolean createBooking(int userId, int ticketId, Timestamp bookingDate) throws SQLException {
        String query = "INSERT INTO bookings (user_id, ticket_id, booking_date) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, ticketId);
            stmt.setTimestamp(3, bookingDate);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0; // Returns true if the booking was created successfully
        }
    }

    // Retrieve all bookings from the database
    public List<Booking> getAllBookings() throws SQLException {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT booking_id, user_id, ticket_id, booking_date FROM Bookings";

        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int bookingId = rs.getInt("booking_id");
                int userId = rs.getInt("user_id");
                int ticketId = rs.getInt("ticket_id");
                Timestamp bookingDate = rs.getTimestamp("booking_date");

                bookings.add(new Booking(bookingId, userId, ticketId, bookingDate));
            }
        }
        return bookings;
    }

    // Update a booking record in the database
    public boolean updateBooking(int bookingId, int userId, int ticketId) throws SQLException {
        String query = "UPDATE Bookings SET user_id = ?, ticket_id = ? WHERE booking_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, ticketId);
            stmt.setInt(3, bookingId);
            return stmt.executeUpdate() > 0;
        }
    }

    // Delete a booking record from the database
    public boolean deleteBooking(int bookingId) throws SQLException {
        String query = "DELETE FROM Bookings WHERE booking_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, bookingId);
            return stmt.executeUpdate() > 0;
        }
    }
    
  //   Method to get the latest booking ID for a specific user and ticket
  public int getLatestBookingId(int userId, int ticketId) throws SQLException {
      String query = "SELECT booking_id FROM Bookings WHERE user_id = ? AND ticket_id = ? ORDER BY booking_date DESC LIMIT 1";
      try (PreparedStatement stmt = connection.prepareStatement(query)) {
          stmt.setInt(1, userId);
          stmt.setInt(2, ticketId);

          try (ResultSet rs = stmt.executeQuery()) {
              if (rs.next()) {
                  return rs.getInt("booking_id"); // Return the latest booking_id
              } else {
                  return -1; // Indicate no booking was found
              }
          }
      }
  }
  
}
