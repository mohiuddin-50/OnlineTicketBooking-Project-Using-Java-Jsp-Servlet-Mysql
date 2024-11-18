<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%-- <!-- Session Check -->
<c:if test="${empty sessionScope.admin}">
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
    <title>Manage Bookings</title>
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
                    <a class="nav-link" href="adminLogin.jsp">Logout</a>
                </li>
            </ul>
        </div>
    </nav>

    <!-- Manage Bookings Table -->
    <div class="container mt-5">
        <h2>Manage Bookings</h2>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Booking ID</th>
                    <th>User</th>
                    <th>Ticket ID</th>
                    <th>Destination</th>
                    <th>Travel Date</th>
                    <th>Booking Date</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="booking" items="${bookings}">
                    <tr>
                        <td>${booking.bookingId}</td>
                        <td>${booking.userId}</td>
                        <td>${booking.ticketId}</td>
                        <td>${booking.destination}</td>
                        <td>${booking.travelDate}</td>
                        <td>${booking.bookingDate}</td>
                        <td>
                            <a href="CancelBookingServlet?bookingId=${booking.bookingId}" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to cancel this booking?')">Cancel</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <!-- Optional JavaScript -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
