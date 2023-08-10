<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <title>ChatWorld</title>
  <link rel="stylesheet" href="CSS/styles.css" />
  <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap" rel="stylesheet" />
  <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
</head>

<body>
  <div class="loginpage">
    <div class="left">
        <h1>Welcome Back.</h1>
        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
          Curabitur et est sed felis aliquet sollicitudin</p>
    </div>
    <div class="right">
      <div class="login-box">
        <h1>Login</h1>
        <form action = "check_user" method = "get" enctype = 'multipart/form-data'>
          <!-- <label>Email</label> -->
          <input type="email" placeholder="Email" name = "emailID" required/>
          <!-- <label>Password</label> -->
          <input type="password" placeholder="Password" name = "password" required/>
          <button type = "submit" class = "btnSub">Submit</button>
        </form>
        <p class="para-2">
          Not have an account? <a href="signup.jsp">Sign Up Here</a>
        </p>
      </div>
      
    </div>
  </div>

</body>

</html>

