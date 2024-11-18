package com.mohiuddin.onlineticketbookingwebapp.model;

import java.math.BigDecimal;
import java.sql.Date;

public class Ticket {
    private int ticketId;
    private String destination;
    private Date travelDate;  // Using java.sql.Date to match with MySQL DATE type
    private BigDecimal price;

    public Ticket(int ticketId, String destination, Date travelDate, BigDecimal price) {
        this.ticketId = ticketId;
        this.destination = destination;
        this.travelDate = travelDate;
        this.price = price;
    }

    // Getters and Setters
    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(Date travelDate) {
        this.travelDate = travelDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
