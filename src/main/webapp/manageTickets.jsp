<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!-- Session Check -->
<%-- <c:if test="${empty sessionScope.admin}">
    <c:redirect url="adminLogin.jsp" />
</c:if> --%>

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
    <title>Manage Tickets</title>
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
                    <a class="nav-link" href="BookingHistoryServlet">Booking History</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="SearchAndBookServlet">Search and Book Tickets</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="ManageBookings.jsp">Manage Bookings</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="ManageTickets.jsp">Manage Tickets</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="adminLogin.jsp">Logout</a>
                </li>
            </ul>
        </div>
    </nav>

    <!-- Manage Tickets Table -->
    <div class="container mt-5">
        <h2>Manage Tickets</h2>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Ticket ID</th>
                    <th>Destination</th>
                    <th>Travel Date</th>
                    <th>Price</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="ticket" items="${tickets}">
                    <tr>
                        <td>${ticket.ticketId}</td>
                        <td>${ticket.destination}</td>
                        <td>${ticket.travelDate}</td>
                        <td>${ticket.price}</td>
                        <td>
                            <a href="EditTicketServlet?ticketId=${ticket.ticketId}" class="btn btn-warning btn-sm">Edit</a>
                            <a href="DeleteTicketServlet?ticketId=${ticket.ticketId}" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this ticket?')">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="addTicket.jsp" class="btn btn-primary">Add New Ticket</a>
    </div>

    <!-- Optional JavaScript -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>