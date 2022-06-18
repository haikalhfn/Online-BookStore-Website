
package Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.servlet.annotation.*;

import config.*;
import config.ImageUploader;
import Bean.*;
import database.AdminLoginDao;
import database.ViewbookDao;

@WebServlet("/product")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		maxFileSize = 1024 * 1024 * 10, // 10 MB
		maxRequestSize = 1024 * 1024 * 100 // 100 MB
)

public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// Admin not logged in
		Object loggedIn = req.getSession(true).getAttribute("adminLoggedIn");
		if (loggedIn == null) {
			res.sendRedirect("loginAdmin.jsp");
			return;
		}

		// ADD
		if (req.getParameter("addProduct") != null) {
			req.setAttribute("supplierList", AdminLoginDao.getAll());
			RequestDispatcher rd = req.getRequestDispatcher("BookUpdate.jsp");
			rd.forward(req, res);
		}

		// UPDATE
		 else if (req.getParameter("updateProduct") != null) {
			
			req.setAttribute("supplierList", AdminLoginDao.getAll());
			int productId = Integer.parseInt(req.getParameter("productId"));
			req.setAttribute("product", ViewbookDao.getOne(productId));

			RequestDispatcher rd = req.getRequestDispatcher("BookUpdate.jsp");
			rd.forward(req, res);
		}
		
		else if (req.getParameter("deleteProduct") != null) {
			
			ViewbookDao.deleteOne(Integer.parseInt(req.getParameter("productId")));
			res.sendRedirect("product");
			
		}

		// DISPLAY
		else {
			try {
				req.setAttribute("productList", ViewbookDao.getAll());

				RequestDispatcher rd = req.getRequestDispatcher("product.jsp");
				rd.forward(req, res);

			} catch (Throwable theException) {
				System.out.println(theException);
			}
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// Admin not logged in
		Object loggedIn = req.getSession(true).getAttribute("adminLoggedIn");
		if (loggedIn == null) {
			res.sendRedirect("home");
			return;
		}

		try {
			String productActivity = req.getParameter("productActivity");
			String uploadPath = "C:\\Users\\acer\\eclipse-workspace\\login\\src\\main\\webapp\\images\\";

			String fileName;
			ViewbookBean product;
			Part part;

			switch (productActivity) {

			case "addProduct":
				System.out.println("ok");
				part = req.getPart("image");

				// Upload image to server
				fileName = ImageUploader.upload(uploadPath, part);

				product = new ViewbookBean();
				System.out.println("meme1");
				product.setName(req.getParameter("name"));
				System.out.println("meme2");
				product.setPublish(req.getParameter("bookpublish"));
				System.out.println("meme3");
				product.setCategory(req.getParameter("bookcategory"));
				System.out.println("meme3");
				product.setDescription(req.getParameter("description"));
				System.out.println("meme5");
				product.setPrice(Double.parseDouble(req.getParameter("price")));
				System.out.println("meme6");
				product.setQuantity(Integer.parseInt(req.getParameter("quantity")));
				System.out.println("meme7");
				product.setImage("images/" + fileName);
				System.out.println("meme8");
				product.setAdminid(Integer.parseInt(req.getParameter("Adminid")));
				System.out.println("meme9");
				
				
				
				
				
		

				ViewbookDao.addOne(product);

				res.sendRedirect("product");
				
				break;

			case "deleteProduct":
				ViewbookDao.deleteOne(Integer.parseInt(req.getParameter("productId")));
				res.sendRedirect("product");
				break; 

			case "updateProduct":
				product = new ViewbookBean();

				// New image uploaded
				if (req.getPart("image").getSize() != 0) {
					part = req.getPart("image");

					// Upload image to server
					fileName = ImageUploader.upload(uploadPath, part);

					product.setImage("images/" + fileName);
					System.out.println(fileName + " uploaded");
				} else {
					product.setImage(req.getParameter("image"));
				}

				product.setId(Integer.parseInt(req.getParameter("id")));
				product.setName(req.getParameter("name"));
				product.setPublish(req.getParameter("bookpublish"));
				product.setCategory(req.getParameter("bookcategory"));
				product.setDescription(req.getParameter("description"));
				product.setPrice(Double.parseDouble(req.getParameter("price")));
				product.setQuantity(Integer.parseInt(req.getParameter("quantity")));
				System.out.println(product.getQuantity());
				product.setAdminid(Integer.parseInt(req.getParameter("Adminid")));
				
				ViewbookDao.updateOne(product);
				
				

				res.sendRedirect("product");
				break;

			default:
				res.sendRedirect("product");
				break;
			}
		} catch (Throwable theException) {
			System.out.println(theException);
		}
	}
}
