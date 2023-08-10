package servlets;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import database.Database;

/**
 * Servlet implementation class Add_user
 */
@WebServlet("/Add_user")
@MultipartConfig
public class Add_user extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Add_user() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		boolean x = true;
		String firstname = request.getParameter("firstName");
		String secondname = request.getParameter("lastName");
		String emailID = request.getParameter("emailID");
		String password = request.getParameter("password");
		
		Connection con;
		try {
			Part file = request.getPart("img");
			
			String filename = file.getSubmittedFileName();
			String upload_path = "C:/Users/Shirso/git/chatworld_app/chatworld_app/src/main/webapp/images/" + filename;
			
			FileOutputStream fs = new FileOutputStream(upload_path);
			InputStream is = file.getInputStream();
			
			byte[] data = new byte[is.available()];
			is.read(data);
			fs.write(data);
			fs.close();
			
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
				String query = "INSERT INTO users (name,emailID, password, image) VALUES(?, ?, ?, ?)";
				PreparedStatement preparedStatement = con.prepareStatement(query);
				preparedStatement.setString(1, firstname + secondname);
				preparedStatement.setString(2, emailID);
				preparedStatement.setString(3, password);
				preparedStatement.setString(4, filename);
				
				if(preparedStatement.executeUpdate() == 1)
					System.out.println("Data inserted.");
				
				
				response.sendRedirect("index.jsp");
			}
			else{
				 //%> <!--  <script>alert("User already exists")</script>-->	<%
				//wait(100);
				response.sendRedirect("user_found.jsp");
				//out.println("User already exits.");
				//%> <button id = "back" name ="back" onclick = "window.location.href = 'index.jsp';"> Go back</button> <%
			}
					
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
