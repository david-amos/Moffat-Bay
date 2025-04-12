<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="includes/header.jspf" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Moffat Bay - Registration</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="/Moffat-Bay/css/style.css">
</head>
<body>
<div style="margin-left: 10%; margin-right: 10%; margin-bottom: 10%; border: 2px solid grey; background-color: #fff;">
	<h3>User Login</h3>
	<hr>
	<% String error = (String)request.getAttribute("loginError");
	if (error != null){
		
		%>
		<p style="color: red;"> <%= error %></p>
		<% } %>
	<!-- Will route to doPost method in LodgeServlet -->
	<form method="POST" action="LodgeServlet">
		<table style="margin: 5%">
		<tr><th colspan="2">Welcome Back!</th></tr>
		<tr><td>Please enter your your details</td></tr>
		<tr><td><label>Email Address</label></td></tr>
		<tr><td colspan="2"><input type="email" name ="userEmail" required></td></tr>
		<tr><td><label>Password</label></td></tr>
		<tr><td colspan="2"><input type="password" name="password" required></td></tr>
		<tr><td><input type="submit" value="Sign in" name="submit"></td></tr>
		</table>
		
	</form>
</div>
</body>
