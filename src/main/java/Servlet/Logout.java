package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		Object adminLoggedIn = req.getSession(true).getAttribute("adminLoggedIn");
		Object userLoggedIn = req.getSession(true).getAttribute("loggedIn");
		
		// Admin session exists
		if (adminLoggedIn != null) {
			req.getSession(true).invalidate();
			res.sendRedirect("index.html");
		}
		// Customer  session exists
		else if (userLoggedIn != null) {
			req.getSession(true).invalidate();
			res.sendRedirect("index.html");
		} else {
			res.sendRedirect("index.html");
		}
	}
}
