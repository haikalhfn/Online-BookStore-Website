package Servlet;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.UserLoginBean;
import database.UserLoginDao;





@WebServlet("/login")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// User logged in
		Object loggedIn = req.getSession(true).getAttribute("loggedIn");
		if (loggedIn != null) {
			res.sendRedirect("home");
			return;
		}

		RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
		rd.forward(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// User logged in
		Object loggedIn = req.getSession(true).getAttribute("loggedIn");
		if (loggedIn != null) {
			res.sendRedirect("home");
			return;
		}

		try {
			UserLoginBean customer = new UserLoginBean();
			customer.setUsername(req.getParameter("username"));
			customer.setPassword(req.getParameter("password"));
			customer = UserLoginDao.login(customer);

			if (customer.isValid()) {
				// Logged-in page
				req.getSession(true).invalidate();
				HttpSession session = req.getSession(true);

				session.setAttribute("loggedIn", true);
				session.setAttribute("id", customer.getUserid());
				session.setAttribute("username", customer.getUsername());
				session.setAttribute("Email", customer.getEmail());
				session.setAttribute("phone", customer.getPhone());
				

				res.sendRedirect("home");
			} else {
				// Error page
				RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
				req.setAttribute("errorMessage", "Invalid Credentials");
				rd.forward(req, res);
			}
		} catch (Throwable theException) {
			System.out.println(theException);
		}
	}
}