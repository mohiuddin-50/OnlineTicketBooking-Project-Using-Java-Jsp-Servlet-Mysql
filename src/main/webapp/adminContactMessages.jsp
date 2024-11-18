<%@ page import="java.sql.*" %>
<%@ page import="com.mohiuddin.onlineticketbookingwebapp.factory.DBConnection" %>
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
%>
 --%>
<html>
<head>
    <title>Contact Messages</title>
</head>
<body>
    <h2>Contact Messages</h2>
    <table border="1">
        <tr>
            <th>Name</th>
            <th>Email</th>
            <th>Message</th>
            <th>Timestamp</th>
        </tr>
        <% 
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM contacts ORDER BY timestamp DESC";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
        %>
        <tr>
            <td><%= rs.getString("name") %></td>
            <td><%= rs.getString("email") %></td>
            <td><%= rs.getString("message") %></td>
            <td><%= rs.getTimestamp("timestamp") %></td>
        </tr>
        <% 
            }
            rs.close();
            stmt.close();
            conn.close();
        %>
    </table>
</body>
</html>
