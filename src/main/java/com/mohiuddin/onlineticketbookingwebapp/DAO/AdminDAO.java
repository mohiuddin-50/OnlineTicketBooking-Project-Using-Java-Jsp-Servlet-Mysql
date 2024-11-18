package com.mohiuddin.onlineticketbookingwebapp.DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mohiuddin.onlineticketbookingwebapp.factory.DBConnection;
//import model.Admin;


public class AdminDAO {

    public boolean authenticateAdmin(String username, String password) {
        boolean isAuthenticated = false;
        try (Connection con = DBConnection.getConnection()) {
            String query = "SELECT * FROM admin WHERE username = ? AND password = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                isAuthenticated = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isAuthenticated;
    }
}

