package Servlet;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.*;
import database.*;

@WebServlet("/order")

public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// User not logged in
         Object loggedIn = req.getSession(true).getAttribute("loggedIn");
		
		if (loggedIn == null ) {
			res.sendRedirect("login");
			
			return;
		
		}		


		try {
			int userId = (int) req.getSession(true).getAttribute("id");

			req.setAttribute("cartList", CartDao.getUserCart(userId));

			RequestDispatcher rd = req.getRequestDispatcher("Order.jsp");
			rd.forward(req, res);

		} catch (Throwable theException) {
			System.out.println(theException);
		}
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// user not logged in
		Object loggedIn = req.getSession(true).getAttribute("loggedIn");
		if (loggedIn == null) {
			res.sendRedirect("home");
			return;
		}

		try {
			List<Integer> cartIds = new LinkedList<>();
			for (String cartId : req.getParameterValues("cartId")) {
				cartIds.add(Integer.parseInt(cartId));
			}

			List<CartBean> cartList = CartDao.getUserCart(Integer.parseInt(req.getParameter("userId")));

			List<OrderBean> purchaseList = new LinkedList<>();

			for (CartBean cart : cartList) {
				OrderBean purchase = new OrderBean();

				purchase.setDate((new Date()).toString());			
				purchase.setQuantity(1);
				purchase.setTotal(cart.getProduct().getPrice());
				purchase.setUserid(cart.getCustId());
				purchase.setBookid(cart.getProdId());

				UserLoginBean customer = UserLoginDao.getOne(cart.getCustId());
				purchase.setCustomer(customer);

				OrderDao.addOne(purchase);

				ViewbookBean product = ViewbookDao.getOne(cart.getProdId());
				product.setQuantity(product.getQuantity() - 1);
				ViewbookDao.updateOne(product);
				purchase.setProduct(product);

				CartDao.removeFromCart(cart.getId());

				purchaseList.add(purchase);
			}

			req.setAttribute("purchaseList", purchaseList);
			RequestDispatcher rd = req.getRequestDispatcher("receipt");
			rd.forward(req, res);
		} catch (Throwable theException) {
			System.out.println(theException);
		}
	}
}
