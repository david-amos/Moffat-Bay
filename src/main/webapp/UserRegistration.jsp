<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="includes/header.jspf" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Moffat Bay - Registration</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<!-- Will route to doPost method in LodgeServlet -->
	<form method="POST" action="LodgeServlet">
		<label>First Name</label>
		<input type="text" name ="firstName" required><br>
		<label>Last Name</label>
		<input type="text" name ="lastName" required><br>
		<label>Email Address</label>
		<input type="email" name ="userEmail" required><br>
		<label>Date of Birth</label>
		<input type="date" name ="birthDate" required><br>
		<label>Phone Number</label>
		<input type="tel" name ="phoneNumber" placeholder="123-45-678" pattern="[0-9]{3}-?[0-9]{3}-?[0-9]{4}" required><br>
		<p>Password must be least 8 characters in length and include one number, one upper-case and one lower-case letter</p>
		<label>Password</label>
		<input type="password" name="password" required><br>
		<label>Confirm Password</label>
		<input type="password" name="ConfirmPassword" required><br>
		<input type="submit" value="Signup" name="submit">
		
	</form>
</body>
</html>