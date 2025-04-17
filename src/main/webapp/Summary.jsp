<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="includes/header.jspf" %>
<!DOCTYPE html>
<html>
<head>
<%-- Moffat Bay Lodge Reservation Page
Team Charlie: David Amos, Scott Cacal, Caitlan Nichols, Alexander Zayas
This is a jsp page to handle the display of the page --%>
<%@ page import="beans.User" %>
<%@ page import="beans.Reservation" %>
<%@ page import="beans.Room" %>
<meta charset="UTF-8">
<title>Moffat Bay - Reservations</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="/Moffat-Bay/css/style.css">
</head>
<body>
<div style="margin-left: 10%; margin-right: 10%; margin-bottom: 10%; border: 2px solid grey; background-color: #fff;">
	<h3>Guest Reservations</h3>
	<hr>
	<% String error = (String)request.getAttribute("reservationError");
	if (error != null){
		
		%>
		<p style="color: red;"> <%= error %></p>
		<% } //ensure the user is logged in to allow reservation, if user is present info can be used to pre-populate fields
		User user = (User)request.getSession().getAttribute("User");
		Reservation reservation = (Reservation)request.getSession().getAttribute("Reservation");
		//Room room = (Room)request.getSession().getAttribute("Reservation");		
		if(user != null){ %>
		
	<!-- Will route to doPost method in LodgeServlet -->
	<form method="POST" action="LodgeServlet">
		<table style="margin: 5%">
		<tr><th colspan="2">Reservation Summary</th></tr>

		<tr><td><label>Address</label></td></tr>
		<tr><td><input type="text" name ="addressLine1" value ="<%= reservation.getAddressLine1()%>" disabled></td></tr>
		<tr><td><input type="text" name ="addressLine2" value ="<%= reservation.getAddressLine1()%>" disabled></td></tr>		
		<tr><td><input type="text" name ="phoneNumber" value = "<%= user.getPhoneNumber()%>" disabled></td><td><input type="text" name ="userEmail" value = "<%= user.getUserEmail()%>" disabled></td></tr>

		<tr><td><label>Arrival - Date and Time</label></td></tr>
		<tr><td><input type="datetime-local" name ="arrivalDateTime" value = "<%= reservation.getArrivalDateTime()%>" disabled></td></tr>

		<tr><td><label>Arrival - Date and Time</label></td></tr>
		<tr><td><input type="datetime-local" name ="departureDateTime" value = "<%= reservation.getDepartureDateTime()%>" disabled></td></tr>

		<tr><td><label>Number of Adults</label></td></tr>
		<tr><td><input type="number" name="numAdults" value = "<%= reservation.getNumAdults()%>" disabled></td></tr>
		<tr><td><label>Number of Children</label></td></tr>
		<tr><td><input type="number" name="numChildren" value = "<%= reservation.getNumChildren()%>" disabled></td></tr>		


		<tr><td><label>Room Type</label></td></tr>
		<tr><td><input type="text" name="roomType" value = "<%= reservation.getRoomTypeID() %>" disabled></td></tr>
		

		<tr><td><input type="submit" value="MakeReservation" name="submit"></td></tr>
		</table>
		<% } else {%>
		<p style="color: red;">Please sign in</p>
		<%} %>
	</form>
</div>
</body>
</html>