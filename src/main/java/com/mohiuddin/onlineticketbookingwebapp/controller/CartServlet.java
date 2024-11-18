package com.mohiuddin.onlineticketbookingwebapp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.mohiuddin.onlineticketbookingwebapp.model.CartItem;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Redirect to the cart view page
        request.getRequestDispatcher("viewCart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Retrieve cart items from session, or create a new cart if it doesn't exist
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }

        // Get ticket details from the request
        int ticketId = Integer.parseInt(request.getParameter("ticketId"));
        String destination = request.getParameter("destination");
        double price = Double.parseDouble(request.getParameter("price"));

        // Add ticket to cart
        CartItem item = new CartItem(ticketId, destination, 1, price); // Default quantity is 1
        cart.add(item);

        // Update session with the new cart
        session.setAttribute("cart", cart);

        // Redirect to cart page
        response.sendRedirect("viewCart.jsp");
    }
}
