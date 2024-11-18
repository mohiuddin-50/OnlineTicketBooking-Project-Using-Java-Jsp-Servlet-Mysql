<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Tickets</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark" style="background-color: #007bff;">
        <a class="navbar-brand" href="#">Ticket Booking System</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="AdminBookingHistoryServlet">Booking History</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="AdminSearchAndBookServlet">Search and Book Tickets</a>
                </li>
                
                <li class="nav-item">
                    <a class="nav-link" href="ViewTicketsServlet">CRUD Tickets</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="logout.jsp">Logout</a>
                </li>
            </ul>
        </div>
    </nav>

    <!-- Main Content -->
    <div class="container mt-4">
        <h2>Available Tickets</h2>
        
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Destination</th>
                    <th>Travel Date</th>
                    <th>Price</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <!-- Loop through tickets and display each one -->
                <c:forEach var="ticket" items="${tickets}">
                    <tr>
                        <td>${ticket.ticketId}</td>
                        <td>${ticket.destination}</td>
                        <td>${ticket.travelDate}</td>
                        <td>${ticket.price}</td>
                        <td>
                            <!-- Example action buttons -->
                            <a href="EditTicketServlet?ticketId=${ticket.ticketId}" class="btn btn-primary btn-sm">Edit</a>
                            <a href="DeleteTicketServlet?ticketId=${ticket.ticketId}" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this ticket?')">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <!-- Link to add a new ticket -->
        <a href="AddTicket.jsp" class="btn btn-success mt-3">Add New Ticket</a>
    </div>

    <!-- Optional JavaScript -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
