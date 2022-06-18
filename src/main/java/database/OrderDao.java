package database;

import java.sql.*;
import java.util.*;

import config.*;
import Bean.*;

public class OrderDao {
	static Connection connection = null;
	static ResultSet resultSet = null;

	// Get all purchases
	public static List<OrderBean> getAll() {

		List<OrderBean> purchaseList = new LinkedList<>();
		String sql = "SELECT * FROM ordercust pur JOIN user c ON pur.userid = c.id "
				+ "JOIN book pro ON pur.bookid = pro.id ORDER BY orderid;";
		Statement statement = null;

		// Trace process
		System.out.println("in PurchaseDAO.getAll");

		try {
			// Connect to DB
			connection = ConnectionManager.getConnection();

			statement = connection.createStatement();

			resultSet = statement.executeQuery(sql);

			// Iterate over the ResultSet, add row into object and object into list
			while (resultSet.next()) {

				// Purchase
				OrderBean purchase = new OrderBean();
				purchase.setOrderid(resultSet.getInt("orderid"));
				purchase.setOrdername(resultSet.getString("ordername"));
				purchase.setEmail(resultSet.getString("email"));
				purchase.setTotal(resultSet.getDouble("total"));
				purchase.setQuantity(resultSet.getInt("quantity"));
				purchase.setDate(resultSet.getDate("date").toString());
				

				// Customer
				UserLoginBean customer = purchase.getCustomer();
				customer.setUserid(resultSet.getInt("userid"));
				customer.setUsername(resultSet.getString("username"));				
				customer.setPassword(resultSet.getString("password"));
				customer.setEmail(resultSet.getString("email"));
				customer.setPhone(resultSet.getString("Nophone"));

				// Product
				ViewbookBean product = purchase.getProduct();
				product.setId(resultSet.getInt("id"));
				product.setName(resultSet.getString("bookname"));
				product.setQuantity(resultSet.getInt("quantity"));
				product.setPublish(resultSet.getString("bookpublish"));
				product.setPrice(resultSet.getDouble("bookprice"));
				product.setDescription(resultSet.getString("bookdescription"));
				product.setImage(resultSet.getString("image"));
				product.setAdminid(resultSet.getInt("Adminid"));

				purchaseList.add(purchase);
			}
		} catch (Exception ex) {
			System.out.println("Error in PurchaseDAO.getAll: " + ex);
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
		return purchaseList;
	}

	// Get all purchases by month
	public static List<OrderBean> getAllByMonth() {

		List<OrderBean> purchaseList = new LinkedList<>();
		String sql = "SELECT DATE_FORMAT(date, '%M %Y') AS month, SUM(total) AS sum "
				+ "FROM ordercust GROUP BY month;";
		Statement statement = null;

		// Trace process
		System.out.println("in PurchaseDAO.getAll");

		try {
			// Connect to DB
			connection = ConnectionManager.getConnection();

			statement = connection.createStatement();

			resultSet = statement.executeQuery(sql);

			// Iterate over the ResultSet, add row into object and object into list
			while (resultSet.next()) {

				// Purchase
				OrderBean purchase = new OrderBean();
				purchase.setDate(resultSet.getString("month"));
				purchase.setTotal(resultSet.getDouble("sum"));

				purchaseList.add(purchase);
			}
		} catch (Exception ex) {
			System.out.println("Error in PurchaseDAO.getAllByMonth: " + ex);
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
		return purchaseList;
	}

	// Get only one purchase
	public static OrderBean getOne(int purchaseId) {

		// Preparing some objects/variable
		OrderBean purchase = new OrderBean();
		String sql = "SELECT * from ordercust WHERE orderid=?;";

		PreparedStatement statement = null;

		// Trace process
		System.out.println("In PurchaseDAO.getOne");

		try {
			// Connect to DB
			connection = ConnectionManager.getConnection();
			statement = connection.prepareStatement(sql);

			statement.setInt(1, purchaseId);

			resultSet = statement.executeQuery();

			// Iterate over the ResultSet, add row into object and object into list
			while (resultSet.next()) {

				// Product
				purchase.setOrderid(resultSet.getInt("orderid"));
				purchase.setOrdername(resultSet.getString("ordername"));
				purchase.setEmail(resultSet.getString("email"));
				purchase.setTotal(resultSet.getDouble("total"));
				purchase.setQuantity(resultSet.getInt("quantity"));
				purchase.setDate(resultSet.getDate("date").toString());

				// Customer
				UserLoginBean customer = purchase.getCustomer();
				customer.setUserid(resultSet.getInt("userid"));
				customer.setUsername(resultSet.getString("username"));
				customer.setEmail(resultSet.getString("email"));
				customer.setPhone(resultSet.getString("Nophone"));

				// Product
				ViewbookBean product = purchase.getProduct();
				product.setId(resultSet.getInt("id"));
				product.setName(resultSet.getString("bookname"));
				product.setPublish(resultSet.getString("bookpublish"));
				product.setQuantity(resultSet.getInt("quantity"));
				product.setPrice(resultSet.getDouble("bookprice"));
				product.setDescription(resultSet.getString("bookdescription"));
				product.setImage(resultSet.getString("image"));
				product.setAdminid(resultSet.getInt("Adminid"));
			}
		} catch (Exception ex) {
			System.out.println("Error in PurchaseDAO.getOne " + ex);
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
		return purchase;
	}

	// add one purchase
	public static void addOne(OrderBean purchase) {

		// Preparing some objects/variable
		String sql = "INSERT INTO ordercust (ordername, email, total, quantity, date, userid, bookid)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";

		PreparedStatement statement = null;

		// Trace process
		System.out.println("In PurchaseDAO.addOne");

		try {
			// Connect to DB
			connection = ConnectionManager.getConnection();
			statement = connection.prepareStatement(sql);

			statement.setString(1, purchase.getOrdername());
			statement.setString(2, purchase.getEmail());
			statement.setDouble(3, purchase.getTotal());
			statement.setInt(4, purchase.getQuantity());
			statement.setString(5, purchase.getDate());		
			statement.setInt(6, purchase.getUserid());
			statement.setInt(7, purchase.getBookid());

			statement.executeUpdate();
			System.out.println("New Purchase added to database.");

		} catch (Exception ex) {
			System.out.println("Error in PurchaseDAO.addOne " + ex);
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