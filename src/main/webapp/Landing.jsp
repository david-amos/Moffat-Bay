<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="includes/header.jspf" %>
<!DOCTYPE html>
<html>
<head>
  <title>Welcome to Moffat Bay Lodge</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
  <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>

  <div class="hero-image">
    <img src="images/MoffatBayLodge.png" alt="Lodge at Moffat Bay" style="width:100%; max-height:400px;">
    <h1 class="hero-text">Discover Tranquility</h1>
  </div>

  <div class="info-section">
    <div class="info-card">
      <h2>Relaxation</h2>
      <p>Moffat Bay is a great place to stay with state of the art comfort.</p>
      <a href="About.jsp"><button>About Us</button></a>
    </div>

    <div class="info-card">
      <h2>Attractions</h2>
      <p>Moffat Bay offers lots of great activities to enjoy during your stay.<br>Hiking, kayaking, whale watching, scuba diving, and more!</p>
      <a href="Attractions.jsp"><button>Attractions</button></a>
    </div>

    <div class="info-card">
      <h2>Book Your Stay</h2>
      <p>Get started today and register for a free account!</p>
      <a href="UserRegistration.jsp"><button>Register</button></a>
    </div>
  </div>

</body>
</html>