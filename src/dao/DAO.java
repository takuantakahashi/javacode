package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DAO {
	Connection con = null;
	PreparedStatement ps = null;
	private String url = "jdbc:mysql://localhost:3306/sample?serverTimezone=JST";
	private String dbUser = "root";
	private String dbPassword = "root";
	
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url,dbUser,dbPassword);
			return con;
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
			con = null;
			return con;
		}catch(SQLException e){
			e.printStackTrace();
			con = null;
			return con;
		}
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}
	
	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}
	
	public void connectionClose() {
		try {
			if(ps != null) {
				ps.close();
			}
			if(con != null) {
				con.close();
			}
		}catch(SQLException e) {}
	}
}
