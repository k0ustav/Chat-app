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
		boolean x = true;
		String firstname = request.getParameter("firstName");
		String secondname = request.getParameter("lastName");
		String emailID = request.getParameter("emailID");
		String password = request.getParameter("password");
		Connection con;
		try {
			ServletContext contx = getServletContext();
			Database db = (Database) contx.getAttribute("database");
			
			if(db == null){
				db = new Database();
			}
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			//Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "chatApp", "pass");
			con = db.getConnection();
			Statement smt = con.createStatement();
			ResultSet set = smt.executeQuery("select * from users");
			
			while(set.next()){
				
				if(set.getString(2).equals(emailID)){
					x = false;
					break;
				}
			}
			if(x)
			{
				String query = "INSERT INTO users (name,emailID, password) VALUES(?, ?, ?)";
				PreparedStatement preparedStatement = con.prepareStatement(query);
				preparedStatement.setString(1, firstname + secondname);
				preparedStatement.setString(2, emailID);
				preparedStatement.setString(3, password);
				
				if(preparedStatement.executeUpdate() == 1)
					System.out.println("Data inserted.");
				
				response.sendRedirect("index.jsp");
			}
			else{
				 %> <!--  <script>alert("User already exists")</script>-->	<%
				//wait(100);
				//response.sendRedirect("register.jsp");
				out.println("User already exits.");
				%> <button id = "back" name ="back" onclick = "window.location.href = 'index.jsp';"> Go back</button> <%
			}
					
		}
		catch(Exception e){
			e.printStackTrace();
		}
	%>

</body>
</html>