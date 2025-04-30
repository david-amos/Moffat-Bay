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
<link rel="stylesheet" type="text/css" href="/Moffat-Bay/css/attractions.css">
</head>
<body>
	<div class="attractions-container">
		<div class="attractions-header">Moffat Bay Attractions</div>
	
		<div class="adventure-hero">
			<img src="images/MoffatBayLodge.png" alt="Lodge at Moffat Bay">
			<div class="hero-text">
				<h3>Island Adventures Await</h3>
				<p>
					Bring the Family and Explore the Wonders of Joviedsa Island.<br>
					Plan your next adventure at Moffat Bay Lodge Today!
				</p>
				<a href="Reservations.jsp"><button class="reserve-btn">Reserve a Room now</button></a>
			</div>
		</div>
	
		<div class="activities-grid">
			<div class="activity">
				<img src="images/hiking.png" alt="Hiking">
				<p>Discover miles of forest trails with breathtaking views of the coastline and wildlife habitats. Guided and solo hikes available.</p>
			</div>
			<div class="activity">
				<img src="images/kayaking.png" alt="Kayaking">
				<p>Paddle through the serene waters of Moffat Bay. Rentals and tours offered daily for all skill levels.</p>
			</div>
			<div class="activity">
				<img src="images/whale-watching.png" alt="Whale Watching">
				<p>Experience orca sightings up close with our partner tour guides. Best viewing times: May–September.</p>
			</div>
			<div class="activity">
				<img src="images/SCUBA.png" alt="SCUBA diving">
				<p>Explore underwater reefs and marine life in the pristine waters off the island. Equipment rentals and certified instructors available.</p>
			</div>
		</div>
	</div>
	</body>
</html>