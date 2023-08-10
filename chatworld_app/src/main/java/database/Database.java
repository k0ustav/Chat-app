package database;

import java.sql.*;

public class Database {
	Connection con;
	public Connection getConnection() {	
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "chatApp", "pass");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}
