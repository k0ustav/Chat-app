package servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.sql.*;

import database.Database;

/**
 * Servlet implementation class Check_user
 */
@WebServlet("/Check_user")
public class Check_user extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Check_user() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		boolean x = false;
		//String firstname = request.getParameter("firstName");
		//String secondname = request.getParameter("lastName");
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
					HttpSession session = request.getSession();
					session.setAttribute("active", "active");
					session.setAttribute("image", set.getString(4));
					//session.setAttribute("emailID", emailID);
					r.forward(request,response);
					x = true;
				}
			}
			if(!x) {
				response.sendRedirect("user_not_found.jsp");
			}
		}
		catch(Exception e){
			e.printStackTrace();	
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
