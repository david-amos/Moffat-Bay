<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="includes/header.jspf" %>
<!DOCTYPE html>
<html>
<head>
<%-- Moffat Bay UserRegistration
Team Charlie: David Amos, Scott Cacal, Caitlan Nichols, Alexander Zayas
This file handles the display of the user registration --%>
<meta charset="UTF-8">
<title>Moffat Bay - Registration</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="/Moffat-Bay/css/style.css">
</head>
<body>
<div style="margin-left: 10%; margin-right: 10%; margin-bottom: 10%; border: 2px solid grey; background-color: #fff;">
	<h3>Guest Registration</h3>
	<hr>
	<% String error = (String)request.getAttribute("registrationError");
	if (error != null){
		
		%>
		<p style="color: red;"> <%= error %></p>
		<% } %>
	<!-- Will route to doPost method in LodgeServlet -->
	<form method="POST" action="LodgeServlet">
		<table style="margin: 5%">
		<tr><th colspan="2">Create an account to book your stay!</th></tr>
		<tr><td><label>First Name</label></td>
		<td><label>Last Name</label></td></tr>
		<tr>
		<td><input type="text" name ="firstName" required></td>
		<td><input type="text" name ="lastName" required></td>
		</tr>
		<tr><td><label>Email Address</label></td></tr>
		<tr><td colspan="2"><input type="email" name ="userEmail" required></td></tr>
		<tr><td><label>Date of Birth</label></td></tr>
		<tr><td colspan="2"><input type="date" name ="birthDate" value="2000-01-01" required></td></tr>
		<tr><td><label>Phone Number</label></td></tr>
		<tr><td colspan="2"><input type="tel" name ="phoneNumber" placeholder="123-456-7890" pattern="[0-9]{3}-?[0-9]{3}-?[0-9]{4}" required></td></tr>
		<tr><td colspan="2"><p>Password must be least 8 characters in length and include one number, one upper-case and one lower-case letter</p></td></tr>
		<tr><td><label>Password</label></td></tr>
		<tr><td colspan="2"><input type="password" name="password" required></td></tr>
		<tr><td><label>Confirm Password</label></td></tr>
		<tr><td colspan="2"><input type="password" name="confirmPassword" required></td></tr>
		<tr><td><input type="submit" value="Signup" name="submit"></td></tr>
		</table>
		
	</form>
</div>
</body>
</html>