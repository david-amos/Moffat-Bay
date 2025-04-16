<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="includes/header.jspf" %>
<!DOCTYPE html>
<html>
<head>
<%-- Moffat Bay Lodge Reservation Page
Team Charlie: David Amos, Scott Cacal, Caitlan Nichols, Alexander Zayas
This is a jsp page to handle the display of the page --%>
<%@ page import="beans.User" %>
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
		if(user != null){ %>
		
	<!-- Will route to doPost method in LodgeServlet -->
	<form method="POST" action="LodgeServlet">
		<table style="margin: 5%">
		<tr><th colspan="2">Lodge Reservation</th></tr>
		<tr><td><label>First Name</label></td>
		<td><label>Last Name</label></td></tr>
		<tr>
		<td><input type="text" name ="firstName" value = "<%= user.getFirstName()%>" disabled></td>
		<td><input type="text" name ="lastName" value = "<%= user.getLastName()%>" disabled></td>
		</tr>
		<tr><td><label>Address</label></td></tr>
		<tr><td colspan="2"><input type="text" name ="addressLine1" placeholder="Street Address" required></td></tr>
		<tr><td colspan="2"><input type="text" name ="addressLine2" placeholder="Street Address Line 2"></td></tr>
		<tr><td><input type="text" name ="addressCity" placeholder="City" required></td><td><input type="text" name ="addressState" placeholder="State / Province" required></td></tr>
		<tr><td colspan="2"><input type="text" name ="addressZip" placeholder="Postal / Zip" maxlength="5" required></td></tr>
		<tr><td><label>Phone Number</label></td><td><label>Email</label></td></tr>
		<tr><td><input type="text" name ="phoneNumber" value = "<%= user.getPhoneNumber()%>" disabled></td><td><input type="text" name ="userEmail" value = "<%= user.getUserEmail()%>" disabled></td></tr>
		<tr><td><label>Arrival - Date and Time</label></td></tr>
		<tr><td colspan="2"><input type="datetime-local" name ="arrivalDateTime" required></td></tr>
		<tr><td><label>Departure - Date and Time</label></td></tr>
		<tr><td colspan="2"><input type="datetime-local" name ="departureDateTime" required></td></tr>
		<tr><td><label>Number of Adults</label></td>
		<td><label>Number of Children</label></td></tr>
		<tr>
		<td><input type="number" name ="numAdults" min="1" pattern="[0-9]*" required></td>
		<td><input type="number" name ="numChildren" min="0" pattern="[0-9]*" required></td>
		</tr>
		<tr><td><label>Room Type</label></td></tr>
		<tr><td colspan="2"><input type="radio" name="roomType" value = "Double Full" checked><label for="Double Full">Double Full</label>
			<input type="radio" name="roomType" value = "Queen"><label for="Queen">Queen</label>
			<input type="radio" name="roomType" value = "Double Queen"><label for="Double Queen">Double Queen</label>
			<input type="radio" name="roomType" value = "King"><label for="King">King</label>
		</td></tr>
		<tr><td><input type="submit" value="Reserve" name="submit"></td></tr>
		</table>
		<% } else {%>
		<p style="color: red;">Please sign in</p>
		<%} %>
	</form>
</div>
</body>
</html>