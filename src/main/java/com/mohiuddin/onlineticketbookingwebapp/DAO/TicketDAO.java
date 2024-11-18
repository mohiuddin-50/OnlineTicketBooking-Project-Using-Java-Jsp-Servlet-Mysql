package com.mohiuddin.onlineticketbookingwebapp.DAO;

import java.math.BigDecimal;
import java.sql.*;
import java.sql.Date;
import java.util.*;

import com.mohiuddin.onlineticketbookingwebapp.model.Ticket;

public class TicketDAO {
    private Connection connection;

    public TicketDAO(Connection connection) {
        this.connection = connection;
    }
    

    public List<Ticket> getAllTickets() throws SQLException {
        List<Ticket> tickets = new ArrayList<>();
        String query = "SELECT * FROM Tickets";
        
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Ticket ticket = new Ticket(
                        rs.getInt("ticket_id"),
                        rs.getString("destination"),
                        rs.getDate("travel_date"),
                        rs.getBigDecimal("price")
                );
                tickets.add(ticket);
            }
        }
        return tickets;
    }

    public Ticket getTicketById(int ticketId) throws SQLException {
        String query = "SELECT * FROM Tickets WHERE ticket_id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, ticketId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Ticket(
                            rs.getInt("ticket_id"),
                            rs.getString("destination"),
                            rs.getDate("travel_date"),
                            rs.getBigDecimal("price")
                    );
                }
            }
        }
        return null;
    }

//    public boolean deleteTicket(int ticketId) throws SQLException {
//        String query = "DELETE FROM Tickets WHERE ticket_id = ?";
//        
//        try (PreparedStatement stmt = connection.prepareStatement(query)) {
//            stmt.setInt(1, ticketId);
//            return stmt.executeUpdate() > 0;
//        }
//    }

    public boolean addTicket(String destination, Date travelDate, BigDecimal price) throws SQLException {
        String query = "INSERT INTO Tickets (destination, travel_date, price) VALUES (?, ?, ?)";
        
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, destination);
            stmt.setDate(2, travelDate);
            stmt.setBigDecimal(3, price);
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean updateTicket(int ticketId, String destination, Date travelDate, BigDecimal price) throws SQLException {
        String query = "UPDATE Tickets SET destination = ?, travel_date = ?, price = ? WHERE ticket_id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, destination);
            stmt.setDate(2, travelDate);
            stmt.setBigDecimal(3, price);
            stmt.setInt(4, ticketId);
            return stmt.executeUpdate() > 0;
        }
    }
    
//    public boolean deleteTicket(int ticketId) throws SQLException {
//        // Check if the ticket is referenced in the bookings table
//        String checkBookingSql = "SELECT COUNT(*) FROM bookings WHERE ticket_id = ?";
//        try (PreparedStatement preparedStatement = connection.prepareStatement(checkBookingSql)) {
//            preparedStatement.setInt(1, ticketId);
//            ResultSet rs = preparedStatement.executeQuery();
//            
//            if (rs.next() && rs.getInt(1) > 0) {
//                // Ticket is being used in a booking, cannot delete
//                return false;
//            }
//        }
//
//        // Proceed to delete ticket if no bookings are found
//        String deleteSql = "DELETE FROM tickets WHERE ticket_id = ?";
//        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSql)) {
//            preparedStatement.setInt(1, ticketId);
//            int rowsAffected = preparedStatement.executeUpdate();
//            return rowsAffected > 0; // Returns true if a row was deleted
//        }
//    }

    public boolean deleteTicket(int ticketId) throws SQLException {
        // Proceed to delete ticket, cascade will handle bookings deletion
        String deleteSql = "DELETE FROM tickets WHERE ticket_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSql)) {
            preparedStatement.setInt(1, ticketId);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0; // Returns true if a row was deleted
        }
    }


}
