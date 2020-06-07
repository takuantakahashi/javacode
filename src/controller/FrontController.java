package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("*.Action")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		try {
			String path = request.getServletPath();
			String name = path.substring(1).replace(".", "");
			String className = "action." + name;
			Action action = (Action)Class.forName(className).newInstance();
			String url = action.execute(request, response);
			request.getRequestDispatcher(url).forward(request, response);
			
		}catch(Exception e) {
			e.printStackTrace(out);
			
		}
		
		
	}

}
