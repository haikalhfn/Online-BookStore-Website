package Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.*;
import database.*;


/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/cart")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Display products in cart
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// User not logged in
		Object loggedIn = req.getSession(true).getAttribute("loggedIn");
		
		if (loggedIn == null ) {
			res.sendRedirect("login");
			
			return;
		
		}
		
		if (req.getParameter("Productadd") != null) {
			req.setAttribute("productList", ViewbookDao.getAll());
			int productId = Integer.parseInt(req.getParameter("productId"));
			int customerId = (int) req.getSession(true).getAttribute("id");
				
			CartBean cart = new CartBean();
			cart.setCustId(customerId);
			cart.setProdId(productId);
			
			CartDao.addToCart(cart);
			int userId = (int) req.getSession(true).getAttribute("id");
			
			req.setAttribute("cartList", CartDao.getUserCart(userId));
			
			RequestDispatcher rd = req.getRequestDispatcher("Cart.jsp");
			rd.forward(req, res);
		}
     
	  
	}


	// Insert to cart or Remove from cart
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// User not logged in
		Object loggedIn = req.getSession(true).getAttribute("loggedIn");
		if (loggedIn == null) {
			res.sendRedirect("login");
			return;
		}

		try {
//			req.setAttribute("product", ProductDAO.getOne(productId));

			String cartActivity = req.getParameter("cartActivity");

			switch (cartActivity) {
			case "addToCart":
				int customerId = (int) req.getSession(true).getAttribute("id");
				int productId = Integer.parseInt(req.getParameter("productId"));

				CartBean cart = new CartBean();
				cart.setCustId(customerId);
				cart.setProdId(productId);

				// Insert product to cart
				CartDao.addToCart(cart);

//				res.setStatus(HttpServletResponse.SC_NO_CONTENT);
				break;

			case "removeFromCart":
				int cartId = Integer.parseInt(req.getParameter("cartId"));

				CartDao.removeFromCart(cartId);

				res.sendRedirect("cart");
				break;
			}

		} catch (Throwable theException) {
			System.out.println(theException);
		}
	}

}
