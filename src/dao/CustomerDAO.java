package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Customer;

public class CustomerDAO extends DAO {
	public List<Customer> search(String name, String password){
		List<Customer> customerLists = null;
		con = getConnection();
		String sql = "SELECT * FROM customer where login=? AND password=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Customer customer = new Customer();
				customerLists = new ArrayList<>();
				customer.setId(rs.getInt("id"));
				customer.setName(rs.getString("login"));
				customer.setPassword(rs.getString("password"));
				customerLists.add(customer);
			}
			return customerLists;
		}catch(SQLException e) {
			e.printStackTrace();
			return customerLists;
		}
		
	}
}
