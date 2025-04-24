<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="includes/header.jspf" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<%-- Moffat Bay Lookup
Team Charlie: David Amos, Scott Cacal, Caitlan Nichols, Alexander Zayas
This file handles the display of the lookup page --%>
<%@ page import="beans.User" %>
<%@ page import="beans.Reservation" %>
<%@ page import="beans.Room" %>
<%@ page import="java.time.temporal.ChronoUnit" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.ArrayList" %>
<meta charset="UTF-8">
<title>Moffat Bay - Lookup Reservation</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="./css/lookup.css">
</head>
<body>
<div class="lookup-container">
    <h3>Reservation Lookup</h3>
    <hr>
    <% String error = (String)request.getAttribute("searchError");
       if (error != null) { %>
        <p style="color: red;"> <%= error %></p>
    <% } 

    // Ensure the user is logged in to allow lookup
    User user = (User)request.getSession().getAttribute("User");
    if(user != null) { %>

    <c:set var="reservations" value="${sessionScope.searchResults}" />

    <form method="POST" action="LodgeServlet">
        <p>Search with email or reservation number</p>
        <input type="text" name="searchText">
        <input type="submit" value="Search" name="submit">
    </form>

    <c:forEach items="${reservations}" var="item">
        <div class="reservation-entry">
            ${item.userEmail} <br>
            ${item.addressLine1} ${item.addressLine2} <br>
            ${item.addressCity} ${item.addressState} ${item.addressZip} <br>
            ${item.arrivalDateTime} <br> 
            ${item.departureDateTime} <br> 
            ${item.numAdults} Adult(s)<br> 
            ${item.numChildren} Children<br> 
            Room Type: 
            <c:choose>
            	<c:when test ="${item.roomTypeID==1}"><br>
            	Double Full Beds
            	</c:when>
            	<c:when test ="${item.roomTypeID==2}"><br>
            	Queen Bed
            	</c:when>
            	<c:when test ="${item.roomTypeID==3}"><br>
            	Double Queen Beds
            	</c:when>
            	<c:when test ="${item.roomTypeID==4}"><br>
            	King Bed
            	</c:when>
            </c:choose>
            
        </div>
    </c:forEach>

    <% } else { %>
        <p style="color: red;">Please sign in</p>
    <% } %>
</div>
</body>
</html>