<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>



<%-- <!-- Session Check -->
<c:if test="${empty sessionScope.admin}">
    <c:redirect url="adminLogin.jsp" />
</c:if>
 --%>
<%-- <%
    // Directly using the implicit session object
    if (session == null || session.getAttribute("admin") == null) {
        response.sendRedirect("adminLogin.jsp"); // Redirect to login if session is invalid or admin not found
        return; // Stop processing the page further
    }
%> --%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .navbar {
            background-color: #007bff;
        }
        .navbar a {
            color: white;
            padding: 15px;
            text-decoration: none;
        }
        .navbar a:hover {
            background-color: #0056b3;
            border-radius: 5px;
        }
        .card {
            background-color: #f8f9fa;
            border: 1px solid #ddd;
        }
        .container {
            padding: 30px 15px;
        }
        .text-primary {
            color: #007bff;
        }
        .lead {
            font-size: 1.25rem;
        }
    </style>
</head>
<body>

    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark">
        <a class="navbar-brand" href="#">Ticket Booking System</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                
                <li class="nav-item">
                    <a class="nav-link" href="AdminSearchAndBookServlet">Search and Book Tickets</a>
                </li>
                
                <li class="nav-item">
                    <a class="nav-link" href="AddTicket.jsp">Add Ticket</a>
                </li>
                
                <li class="nav-item">
                    <a class="nav-link" href="ViewTicketsServlet">CRUD Tickets</a>
                </li>
                
                <li class="nav-item">
                    <a class="nav-link" href="AdminBookingHistoryServlet">Booking History</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="AdminLogoutServlet">Admin-Logout</a>
                </li>
            </ul>
        </div>
    </nav>

    <!-- Admin Dashboard Content -->
    <div class="container mt-5">
        <div class="card shadow-lg p-4 mb-4 bg-white rounded">
            <h2 class="text-center text-primary mb-4">Welcome to the Admin Dashboard</h2>
            <p class="lead text-center">You are logged in as <strong>${sessionScope.admin}</strong>.</p>
        </div>

        <!-- Admin Options -->
        <h3>Admin Options</h3>
        <ul class="list-group">
            <li class="list-group-item"><a href="ViewTicketsServlet">Manage Tickets</a></li>
            <li class="list-group-item"><a href="AdminBookingHistoryServlet">View Bookings</a></li>
            <li class="list-group-item"><a href="adminContactMessages.jsp">View Contact Messages</a></li> <!-- New link for contact messages -->
        </ul>
    </div>

    <!-- Optional JavaScript -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
