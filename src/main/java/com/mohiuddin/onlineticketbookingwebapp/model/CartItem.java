package com.mohiuddin.onlineticketbookingwebapp.model;

public class CartItem {
    private int ticketId;
    private String destination;
    private int quantity;
    private double price;

    // Constructor
    public CartItem(int ticketId, String destination, int quantity, double price) {
        this.ticketId = ticketId;
        this.destination = destination;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and setters
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}