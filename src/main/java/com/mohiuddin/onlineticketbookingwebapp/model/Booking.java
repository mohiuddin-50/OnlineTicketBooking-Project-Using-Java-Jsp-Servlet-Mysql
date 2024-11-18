package com.mohiuddin.onlineticketbookingwebapp.model;

import java.sql.Timestamp;

public class Booking {
    private int bookingId;
    private int userId;
    private int ticketId;
    private Timestamp bookingDate;

    // Constructor with all parameters
    public Booking(int bookingId, int userId, int ticketId, Timestamp bookingDate) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.ticketId = ticketId;
        this.bookingDate = bookingDate;
    }

    // Getters and Setters
    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public Timestamp getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Timestamp bookingDate) {
        this.bookingDate = bookingDate;
    }
}
