package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Customer;
import dao.CustomerDAO;

public class LoginAction extends Action {
	public String execute(HttpServletRequest request, HttpServletResponse response)throws Exception{
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		CustomerDAO dao = new CustomerDAO();
		List<Customer> customerList = dao.search(name, password);
		
		if(customerList != null) {
			request.setAttribute("customer", customerList);
			return "welcome.jsp";
		}
		
		return "error-login.jsp";
	}

}
