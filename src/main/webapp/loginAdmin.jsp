<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Admin Form</title>
</head>
    <title>Login Admin Form</title>
    <link rel="stylesheet"href="logindes.css"/>
 
<body background ="images/white.jpg">
  <h1>Welcome to Online Book Store</h1>
<form action="loginAd" method="post">
  <div class="imgcontainer">
    <img src="images/img_avatar2.jpg" alt="avatar" class="avatar">
  </div>

  <div class="container">
    <label for="username"><b>Admin Username</b></label>
    <input type="text" placeholder="Enter Username" name="username" required>

    <label for="password"><b>Admin Password</b></label>
    <input type="password" placeholder="Enter Password" name="password" required>

    
    <button type="submit">Login</button>
    <label>

  </div>

  <div class="container" style="background-color:#f1f1f1">


  </div>
</form>
</body>
</html>