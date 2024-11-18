<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
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
    <title>Edit Ticket</title>
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
                    <a class="nav-link" href="AddTicket.jsp">Add Ticket</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="ViewTicketsServlet">View Tickets</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="LogoutServlet">Logout</a>
                </li>
            </ul>
        </div>
    </nav>

    <!-- Edit Ticket Form -->
    <div class="container mt-5">
        <h2>Edit Ticket</h2>
        <form action="EditTicketServlet" method="POST">
            <input type="hidden" name="ticket_id" value="${ticket.ticketId}">
            
            <div class="form-group">
                <label for="destination">Destination:</label>
                <input type="text" id="destination" name="destination" value="${ticket.destination}" class="form-control" required><br><br>
            </div>

            <div class="form-group">
                <label for="travel_date">Travel Date:</label>
                <input type="date" id="travel_date" name="travel_date" value="${ticket.travelDate}" class="form-control" required><br><br>
            </div>

            <div class="form-group">
                <label for="price">Price:</label>
                <input type="number" step="0.01" id="price" name="price" value="${ticket.price}" class="form-control" required><br><br>
            </div>

            <input type="submit" value="Update Ticket" class="btn btn-primary">
        </form>
    </div>

    <!-- Optional JavaScript -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
