<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link rel = "stylesheet" href = "CSS/style2.css" /> 
<body>
	<% session.invalidate(); %>
	Thank you for choosing us for your daily communications.
	<button type = "submit" onclick = "window.location.href = 'index.jsp'">Go back</button>
</body>
</html>