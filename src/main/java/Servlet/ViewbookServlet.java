package Servlet;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.ViewbookDao;

@WebServlet("/home")
public class ViewbookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {

			req.setAttribute("productList", ViewbookDao.getAll());

			RequestDispatcher rd = req.getRequestDispatcher("Viewbook.jsp");
			rd.forward(req, res);

		} catch (Throwable theException) {
			System.out.println(theException);
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.sendRedirect("home");
	}
}