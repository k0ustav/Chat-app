package servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import database.Database;
import java.sql.*;

/**
 * Servlet implementation class AddPost
 */
@WebServlet("/AddPost")
public class AddPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPost() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
