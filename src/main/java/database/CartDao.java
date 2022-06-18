package database;

import java.sql.*;
import java.util.*;

import config.*;
import Bean.*;

public class CartDao {
 
	static Connection connection = null;
	static ResultSet resultSet = null;

	// Get all products from user cart
	public static List<CartBean> getUserCart(int userId) {

		List<CartBean> cartList = new ArrayList<>();
		String sql = "SELECT * FROM carts ca JOIN user cu ON ca.cust_id = cu.userid "
				+ "JOIN book p ON ca.prod_id = p.id WHERE ca.cust_id=? ORDER BY cart_id;";

		PreparedStatement statement = null;

		// Trace process
		System.out.println("in CartDAO.getUserCart");

		try {
			// Connect to DB
			connection = ConnectionManager.getConnection();

			// Prepared statement
			statement = connection.prepareStatement(sql);

			statement.setInt(1, userId);

			resultSet = statement.executeQuery();

			// Iterate over the ResultSet, add row into object and object into list
			while (resultSet.next()) {

				// Cart
				CartBean cart = new CartBean();
				cart.setId(resultSet.getInt("cart_id"));
				cart.setCustId(resultSet.getInt("cust_id"));
				cart.setProdId(resultSet.getInt("prod_id"));

				// Customer
				UserLoginBean customer = cart.getCustomer();
				customer.setUserid(resultSet.getInt("userid"));
				customer.setUsername(resultSet.getString("username"));
				customer.setPhone(resultSet.getString("Nophone"));
				customer.setEmail(resultSet.getString("email"));
				System.out.println(resultSet.getString("email"));
				System.out.println(customer.getEmail());

				// Product
				ViewbookBean product = cart.getProduct();
				product.setId(resultSet.getInt("id"));
				product.setName(resultSet.getString("bookname"));
				product.setQuantity(resultSet.getInt("quantity"));
				product.setPrice(resultSet.getDouble("bookprice"));
				product.setDescription(resultSet.getString("bookdescription"));
				product.setPublish(resultSet.getString("bookpublish"));
				product.setImage(resultSet.getString("image"));
				product.setCategory(resultSet.getString("bookcategory"));
				
				cartList.add(cart);

			}
		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}
		// Some exception handling
		finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (Exception e) {
				}
				resultSet = null;
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (Exception e) {
				}
				statement = null;
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (Exception e) {
				}
				connection = null;
			}
		}
		return cartList;
	}
	
	// Get only one product
	public static  CartBean getOne(int id) {
		
		
		ViewbookBean product = new ViewbookBean();	
		CartBean cart = new CartBean();
		int custId = cart.getCustId();
		int prodId = cart.getProdId();
		String sql = "INSERT INTO carts (cust_id, prod_id) VALUES (?, ?);";
		
		// Used to trace the process
		System.out.println("in CartDAO.addToCart");

		PreparedStatement statement = null;

		try {
			// Connect to DB
			connection = ConnectionManager.getConnection();
			statement = connection.prepareStatement(sql);
			
			statement.setInt(1, custId);
			statement.setInt(2, prodId);

			statement.executeUpdate();
			System.out.println("Product added to cart.");

			

			

			
		} catch (Exception ex) {
			System.out.println("Error in CartDAO.addToCart " + ex);
		}
		// Some exception handling
		finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (Exception e) {
				}
				resultSet = null;
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (Exception e) {
				}
				statement = null;
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (Exception e) {
				}
				connection = null;
			}
		}
		return cart;
	}

	// Add one product into user cart
	public static void addToCart(CartBean cart) {

		// Preparing some objects for connection
		PreparedStatement statement = null;
		int custId = cart.getCustId();
		int prodId = cart.getProdId();
		
		

		// Prepared statement
		String sql = "INSERT INTO carts (cust_id, prod_id) VALUES (?, ?);";

		// Used to trace the process
		System.out.println("in CartDAO.addToCart");

		try {
			// Connect to lipan_db
			connection = ConnectionManager.getConnection();

			// Prepared statement
			statement = connection.prepareStatement(sql);

			statement.setInt(1, custId);
			statement.setInt(2, prodId);

			statement.executeUpdate();
			System.out.println("Product added to cart.");

		} catch (Exception ex) {
			System.out.println("Error in CartDAO.addToCart " + ex);
		}
		// Some exception handling
		finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (Exception e) {
				}
				resultSet = null;
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (Exception e) {
				}
				statement = null;
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (Exception e) {
				}
				connection = null;
			}
		}
	}

	// Remove one product into user cart
	public static void removeFromCart(int cartId) {

		// Preparing some objects for connection
		PreparedStatement statement = null;

		// Prepared statement
		String sql = "DELETE FROM carts WHERE cart_id = ?";

		// Used to trace the process
		System.out.println("in CartDAO.removeFromCart");

		try {
			// Connect to lipan_db
			connection = ConnectionManager.getConnection();

			// Prepared statement
			statement = connection.prepareStatement(sql);

			statement.setInt(1, cartId);

			statement.executeUpdate();
			System.out.println("Product remove from cart.");

		} catch (Exception ex) {
			System.out.println("Error in CartDAO.removeFromcart " + ex);
		}
		// Some exception handling
		finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (Exception e) {
				}
				resultSet = null;
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (Exception e) {
				}
				statement = null;
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (Exception e) {
				}
				connection = null;
			}
		}
	}
}