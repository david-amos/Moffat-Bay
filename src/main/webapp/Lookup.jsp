<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="includes/header.jspf" %>
<!DOCTYPE html>
<html>
<head>
<%-- Moffat Bay Lookup
Team Charlie: David Amos, Scott Cacal, Caitlan Nichols, Alexander Zayas
This file handles the display the lookup page --%>
<%@ page import="beans.User" %>
<%@ page import="beans.Reservation" %>
<%@ page import="beans.Room" %>
<meta charset="UTF-8">
<title>Moffat Bay - Lookup Reservation</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="/Moffat-Bay/css/style.css">
</head>
<body>
<div style="margin-left: 10%; margin-right: 10%; margin-bottom: 10%; border: 2px solid grey; background-color: #fff;">
	<h3>Reservation Lookup</h3>
	<hr>
	<% String error = (String)request.getAttribute("searchError");
	if (error != null){
		
		%>
		<p style="color: red;"> <%= error %></p>
		<% } //ensure the user is logged in to allow lookup
		User user = (User)request.getSession().getAttribute("User");
		if(user != null){ %>
		<form method="POST" action="LodgeServlet">
		<p>Search with email or reservation number</p>
		<input type="text" name="searchText"><input type="submit" value="Search" name="submit">
		</form>
		
		<% } else {%>
		<p style="color: red;">Please sign in</p>
		<%} %>
</div>
</body>
</html>