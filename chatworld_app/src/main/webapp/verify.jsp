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
		boolean x = false;
		String firstname = request.getParameter("firstName");
		String secondname = request.getParameter("lastName");
		String emailID = request.getParameter("emailID");
		String password = request.getParameter("password");
		
		//if(email_session.equals(null)){
		
		try {
			/*
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "chatApp", "pass");
			*/
			
			ServletContext contx = getServletContext();
			Database db = (Database) contx.getAttribute("database");
			
			if(db == null){
				db = new Database();
			}
			Connection conn = db.getConnection();
			Statement smt = conn.createStatement();
			ResultSet set = smt.executeQuery("select * from users");
			
			
			while(set.next()){
				if(set.getString(2).equals(emailID) && set.getString(3).equals(password)){
					RequestDispatcher r = request.getRequestDispatcher("chat.jsp");
					Cookie cooky = new Cookie("emailID", emailID);
					response.addCookie(cooky);
					//session.setAttribute("emailID", emailID);
					r.forward(request,response);
					x = true;
				}
			}
			if(!x)
				out.println("Invalid details entered.");
			
		}
		catch(Exception e){
			e.printStackTrace();	
		}
		
%>

</body>
</html>