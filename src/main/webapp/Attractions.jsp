<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="includes/header.jspf" %>
<!DOCTYPE html>
<html>
<head>
<%-- Moffat Bay Lodge Attractions Page
Team Charlie: David Amos, Scott Cacal, Caitlan Nichols, Alexander Zayas
This is a jsp page to handle the display of the attractions page --%>
<%@ page import="beans.User" %>
<meta charset="UTF-8">
<title>Moffat Bay - Attractions</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="/Moffat-Bay/css/style.css">
</head>
<body>
<div style="float: left; margin-left: 10%; margin-right: 10%; margin-bottom: 10%; border: 2px solid grey; background-color: #fff;">
	<h3>Attractions</h3>
	<div style="float: left">
		<img src="images/MoffatBayLodge.png" alt="Lodge at Moffat Bay" style="max-height:200px;">
		<h4>Island Adventures Await</h4>
		<p>
		Bring the Family and Explore the Wonders of Joviedsa Island.
		Plan your next adventure at Moffat Bay Lodge Today!
		</p>
		<a href="Reservations.jsp">Reserve a room now</a>
	</div>
	<div style="float: right">
		<img src="images/hiking.png" alt="Hiking" style="max-height:200px;">
		<p>Discover miles of forest trails with breathtaking views of the coastline and wildlife habitats. Guided and solo hikes available.</p>
		<img src="images/kayaking.png" alt="Kayaking" style="max-height:200px;">
		<p>Paddle through the serene waters of Moffat Bay. Rentals and tours offered daily for all skill levels.</p>
		<img src="images/whale-watching.png" alt="Whale Watching" style="max-height:200px;">
		<p>Experience orca sightings up close with our partner tour guides. Best viewing times: May–September.</p>
		<img src="images/SCUBA.png" alt="SCUBA diving" style="max-height:200px;">
		<p>Explore underwater reefs and marine life in the pristine waters off the island. Equipment rentals and certified instructors available.</p>
	</div>
</div>
</body>
</html>