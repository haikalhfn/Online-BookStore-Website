package Servlet;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.AdminLoginBean;
import database.AdminLoginDao;




@WebServlet("/loginAd")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	


	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// Admin  logged in
		Object adminLoggedIn = req.getSession(true).getAttribute("adminLoggedIn");
		if (adminLoggedIn != null) {
			res.sendRedirect("product");
			return;
		}

		RequestDispatcher rd = req.getRequestDispatcher("loginAdmin.jsp");
		rd.forward(req, res);
	}

	// Login attempt
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, java.io.IOException {
		try {
			AdminLoginBean admin = new AdminLoginBean();
			admin.setUsername(req.getParameter("username"));
			admin.setPassword(req.getParameter("password"));
			admin = AdminLoginDao.login(admin);

			if (admin.isValid()) {
				// Logged-in page
				req.getSession(true).invalidate();
				HttpSession session = req.getSession(true);

				session.setAttribute("Adminid", admin.getAdminid());
				session.setAttribute("adminname", admin.getUsername());
				session.setAttribute("adminLoggedIn", true);

				res.sendRedirect("product");
			} else {
				// Error page
				RequestDispatcher rd = req.getRequestDispatcher("loginAdmin.jsp");
				req.setAttribute("errorMessage", "Invalid Credentials");
				rd.forward(req, res);
			}
		} catch (Throwable theException) {
			System.out.println(theException);
		}
	}
}
	
