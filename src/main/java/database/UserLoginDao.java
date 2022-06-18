package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Bean.UserLoginBean;
import config.*;



public class UserLoginDao {
	static Connection connection = null;
	static ResultSet resultSet = null;

	// Login user
	public static UserLoginBean login(UserLoginBean customer) {

		// Preparing some objects for connection
		PreparedStatement statement = null;
		String username = customer.getUsername();
		String password = customer.getPassword();

		// Prepared statement
		String searchQuery = "SELECT * FROM user WHERE username=? AND password=?;";

		// Used to trace the process
		System.out.println("in CustomerDAO.login");
		System.out.println("Your user name is " + username);
		System.out.println("Your password is " + password);
		System.out.println("Query: " + searchQuery);

		try {
			// Connect to lipan_db
			connection = ConnectionManager.getConnection();

			// Prepared statement
			statement = connection.prepareStatement(searchQuery);

			statement.setString(1, username);
			statement.setString(2, password);

			resultSet = statement.executeQuery();
			boolean more = resultSet.next();

			// If user does not exist
			if (!more) {
				System.out.println("Sorry, you are not a registered user! " + "Please sign up first");
				customer.setValid(false);
			}
			// If user exists
			else if (more) {
				int id = resultSet.getInt("userid");
				String email = resultSet.getString("email");
				String phone = resultSet.getString("Nophone");

				System.out.println("Welcome " + username);
				customer.setUserid(id);
				customer.setEmail(email);
				customer.setPhone(phone);
				customer.setValid(true);
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
		return customer;
	}

	// Register new user
	public static void addCustomer(UserLoginBean customer) {

		// Preparing some objects for connection
		PreparedStatement statement = null;
		String name = customer.getUsername();
		String username = customer.getUsername();
		String password = customer.getPassword();
		String email = customer.getEmail();
		String phone = customer.getPhone();

		// Prepared statement
		String sql = "INSERT INTO user (username, password, email, Nophone) "
				+ "VALUES (?, ?, ?, ?);";

		// Used to trace the process
		System.out.println("in CustomerDAO.addCustomer");

		try {
			// Connect to lipan_db
			connection = ConnectionManager.getConnection();

			// Prepared statement
			statement = connection.prepareStatement(sql);

	
			statement.setString(1, username);
			statement.setString(2, password);
			statement.setString(3, email);
			statement.setString(4, phone);

			statement.executeUpdate();
			System.out.println(username + "added to database.");

		} catch (Exception ex) {
			System.out.println("Cannot add user to db: " + ex);
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

	// Check availability of username
	public static boolean isUsernameAvailable(String username) {

		// Preparing some objects for connection
		PreparedStatement statement = null;
		boolean isAvailable = false;

		// Prepared statement
		String sql = "SELECT username FROM user WHERE username=?;";

		// Used to trace the process
		System.out.println("in CustomerDAO.isUsernameAvailable");

		try {
			// Connect to lipan_db
			connection = ConnectionManager.getConnection();

			// Prepared statement
			statement = connection.prepareStatement(sql);

			statement.setString(1, username);

			resultSet = statement.executeQuery();
			boolean more = resultSet.next();

			// Username available
			if (!more) {
				isAvailable = true;
			}
			// Username not available
			else {
				isAvailable = false;
			}

		} catch (Exception ex) {
			System.out.println("Error in CustomerDAO.isUsernameAvailable " + ex);
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
		return isAvailable;
	}

	// Get only one purchase
	public static UserLoginBean getOne( int custId) {

		// Preparing some objects/variable
		UserLoginBean customer = new UserLoginBean();
		String sql = "SELECT * from user WHERE userid=?;";

		PreparedStatement statement = null;

		// Trace process
		System.out.println("In CustomerDAO.getOne");

		try {
			// Connect to DB
			connection = ConnectionManager.getConnection();
			statement = connection.prepareStatement(sql);

			statement.setInt(1, custId);

			resultSet = statement.executeQuery();

			// Iterate over the ResultSet, add row into object and object into list
			while (resultSet.next()) {
				
				// Customer
				customer.setUserid(resultSet.getInt("userid"));
				customer.setUsername(resultSet.getString("username"));
				customer.setEmail(resultSet.getString("email"));
				customer.setPhone(resultSet.getString("Nophone"));

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
		return customer;
	}
}
