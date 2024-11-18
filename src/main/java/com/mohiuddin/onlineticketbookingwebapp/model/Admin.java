package com.mohiuddin.onlineticketbookingwebapp.model;

public class Admin {
    private String username;
    private String password;

    // Constructors, getters, and setters
    public Admin() {}

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
