
package database;

import java.sql.*;
import java.util.*;

import Bean.*;
import config.*;
import database.*;

public class ViewbookDao {
	static Connection connection = null;
	static ResultSet resultSet = null;

	public static List<ViewbookBean> getAll() {
		// Preparing some objects/variable
		List<ViewbookBean> productList = new LinkedList<>();
		
		String sql = "SELECT * FROM book p JOIN admin s ON (p.Adminid = s.Adminid) ORDER BY id;";

		Statement statement = null;

		// Trace process
		System.out.println("in ViewbookBean.getAll");

		try {
			// Connect to DB
			connection = ConnectionManager.getConnection();

			statement = connection.createStatement();

			resultSet = statement.executeQuery(sql);

			// Iterate over the ResultSet, add row into object and object into list
			while (resultSet.next()) {

				// Product
				ViewbookBean product = new ViewbookBean();
				product.setId(resultSet.getInt("id"));
				product.setName(resultSet.getString("bookname"));
				product.setPublish(resultSet.getString("bookpublish"));
				product.setCategory(resultSet.getString("bookcategory"));
				product.setDescription(resultSet.getString("bookdescription"));
				product.setPrice(resultSet.getDouble("bookprice"));
				product.setQuantity(resultSet.getInt("quantity"));
				product.setImage(resultSet.getString("image"));
				product.setAdminid(resultSet.getInt("Adminid"));
				


				// Supplier
				AdminLoginBean supplier = product.getSupplier();
				supplier.setAdminid(resultSet.getInt("Adminid"));
				supplier.setUsername(resultSet.getString("adminname"));
				supplier.setPhone(resultSet.getInt("adminNophone"));


				productList.add(product);
			}
		} catch (Exception ex) {
			System.out.println("Log In failed: An Exception has occurred! " + ex);
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
		return productList;
	}

	// Get only one product
	public static ViewbookBean getOne(int id) {

		// Preparing some objects/variable
		ViewbookBean product = new ViewbookBean();
		String sql = "SELECT * from book WHERE id=?;";

		PreparedStatement statement = null;

		// Trace process
		System.out.println("in ViewbookDao.getOne");

		try {
			// Connect to DB
			connection = ConnectionManager.getConnection();
			statement = connection.prepareStatement(sql);

			statement.setInt(1, id);

			resultSet = statement.executeQuery();

			// Iterate over the ResultSet, add row into object and object into list
			while (resultSet.next()) {

				// Product
				product.setId(resultSet.getInt("id"));
				product.setName(resultSet.getString("bookname"));
				product.setPublish(resultSet.getString("bookpublish"));
				product.setCategory(resultSet.getString("bookcategory"));
				product.setDescription(resultSet.getString("bookdescription"));
				product.setPrice(resultSet.getDouble("bookprice"));
				product.setQuantity(resultSet.getInt("quantity"));
				product.setImage(resultSet.getString("image"));
				product.setAdminid(resultSet.getInt("Adminid"));
				
				

			}
		} catch (Exception ex) {
			System.out.println("Error in ViewbookDao.getOne " + ex);
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
		return product;
	}

	// Add one product
	public static void addOne(ViewbookBean product) {

		// Preparing some objects for connection
		PreparedStatement statement = null;
		String name = product.getName();
		String publish = product.getPublish();
		String category = product.getCategory();
		String description = product.getDescription();
		double price = product.getPrice();
		int quantity = product.getQuantity();
		String image = product.getImage();
		int Adminid = product.getAdminid();
		
		
		
		

		// Prepared statement
		String sql = "INSERT INTO book (bookname, bookpublish, bookcategory, bookdescription, bookprice, quantity, image, Adminid ) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

		// Used to trace the process
		System.out.println("in ViewbookDao.addOne");

		try {
			// Connect to lipan_db
			connection = ConnectionManager.getConnection();

			// Prepared statement
			statement = connection.prepareStatement(sql);

			statement.setString(1, name);
			statement.setString(2, publish);
			statement.setString(3, category);
			statement.setString(4, description);
			statement.setDouble(5, price);
			statement.setInt(6, quantity);
			statement.setString(7, image);
			statement.setInt(8, Adminid);
			
			
			

			statement.executeUpdate();
			System.out.println("Product added to database.");

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

	public static void deleteOne(int productId) {

		// Preparing some objects for connection
		PreparedStatement statement = null;

		// Prepared statement
		String sql = "DELETE FROM book WHERE id = ?";

		// Used to trace the process
		System.out.println("in ViewbookDao.deleteOne");

		try {
			// Connect to lipan_db
			connection = ConnectionManager.getConnection();

			// Prepared statement
			statement = connection.prepareStatement(sql);

			statement.setInt(1, productId);

			statement.executeUpdate();
			System.out.println("Product remove from database.");

		} catch (Exception ex) {
			System.out.println("Error in ViewbookDao.deleteOne" + ex);
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

	public static void updateOne(ViewbookBean product) {

		// Preparing some objects for connection
		PreparedStatement statement = null;

		// Prepared statement
		String sql = "UPDATE book "
				+ "SET bookname = ?, bookpublish = ?, bookcategory = ?, bookdescription = ?, bookprice =?, quantity = ?,  image = ?, Adminid = ? "
				+ " WHERE id = ?";

		// Used to trace the process
		System.out.println("in ViewbookDao.updateOne");

		try {
			// Connect to lipan_db
			connection = ConnectionManager.getConnection();

			// Prepared statement
			statement = connection.prepareStatement(sql);

			statement.setString(1,product.getName());
			statement.setString(2, product.getPublish());
			statement.setString(3, product.getCategory());
			statement.setString(4, product.getDescription());
			statement.setDouble(5, product.getPrice());
			statement.setInt(6, product.getQuantity());
			statement.setString(7, product.getImage());
			System.out.println("ok");
			statement.setInt(8, product.getAdminid());
			statement.setInt(9, product.getId());
			System.out.println("kayo");
			statement.executeUpdate();
			System.out.println("Product updated.");

		} catch (Exception ex) {
			System.out.println("Error in ViewbookDao.updateOne" + ex);
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
