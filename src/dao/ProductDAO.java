package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Product;

public class ProductDAO extends DAO{
	public List<Product> productAll(){
		List<Product> productLists = new ArrayList<>();;
		con = getConnection();
		String sql = "SELECT * FROM product";
		try {
			ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Product product = new Product();
				product.setProductId(rs.getInt("id"));
				product.setProductName(rs.getString("name"));
				product.setProductPrice(rs.getInt("price"));
				productLists.add(product);
			}
			return productLists;
		}catch(SQLException e) {
			e.printStackTrace();
			productLists = null;
			return productLists;
		}finally {
			connectionClose();
		}
	}
	
}