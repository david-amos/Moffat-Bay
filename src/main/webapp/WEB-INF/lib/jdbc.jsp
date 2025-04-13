<%@ page import="java.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>JDBC Connection Test</title>
</head>
<body>

<h2>Jsp test</h2>

<%
String jdbcURL = "jdbc:mysql://localhost:3306";
String dbUser = "root";
String dbPassword = "pass";  

Connection conn = null;
Statement stmt = null;
ResultSet rs = null;

try {
    // Ensure MySQL Driver is loaded
    Class.forName("com.mysql.cj.jdbc.Driver");

    // Establish Connection
    conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
    stmt = conn.createStatement();
    rs = stmt.executeQuery("SELECT * FROM books");

    out.println("<h3>Available Books:</h3>");
    while (rs.next()) {
        out.println("<p>" + rs.getString("title") + " - $" + rs.getDouble("price") + "</p>");
    }
} catch (Exception e) {
    out.println("<p>Error: " + e.getMessage() + "</p>");
} finally {
    if (rs != null) rs.close();
    if (stmt != null) stmt.close();
    if (conn != null) conn.close();
}
%>

</body>
</html>
