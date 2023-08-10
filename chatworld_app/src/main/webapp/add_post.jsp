<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import = "java.io.IOException, java.sql.*, database.Database" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		/*
		String emailID = (String)session.getAttribute("emailID");
		String text = request.getParameter("message");
	
		if(emailID != null || text.equals(null)){
		
		*/
		
		String emailID = "";
		String text = request.getParameter("message");
		Cookie cookies[] = request.getCookies();
		
		for(Cookie c: cookies){
			if(c.getName().equals("emailID")){
				emailID = c.getValue();
			}
		}
		
		try {
			/*Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "chatApp", "pass");*/
			
			ServletContext contx = getServletContext();
			Database db = (Database) contx.getAttribute("database");
			
			if(db == null){
				db = new Database();
			}
			Connection conn = db.getConnection();
			String query = "INSERT INTO posts (sender, post) VALUES(?, ?)";
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, emailID);
			preparedStatement.setString(2, text);
			preparedStatement.executeUpdate();
			
			/*
			if(preparedStatement.executeUpdate() == 1)
				System.out.println("Data inserted.");
			*/
			/*
			Cookie cookie = new Cookie("emailID", emailID);
			response.addCookie(cookie);
			RequestDispatcher r = request.getRequestDispatcher("chat.jsp");
			r.forward(request, response);
			*/
			
			response.sendRedirect("chat.jsp");
		
		}catch(Exception e){
			e.printStackTrace();
		}
		/*}
		else
			out.println("Please login.");
		*/
	%>

</body>
</html>