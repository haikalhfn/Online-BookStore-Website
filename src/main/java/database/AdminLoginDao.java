package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import Bean.AdminLoginBean;
import config.ConnectionManager;



public class AdminLoginDao {
	static Connection connection = null;
	static ResultSet resultSet = null;

	public static AdminLoginBean login(AdminLoginBean admin) {

		// preparing some objects for connection 		
				PreparedStatement statement = null;
				String username = admin.getUsername();
				String password = admin.getPassword();
				String searchQuery = "select * from admin where adminname='" 
						+ username + "' AND adminpassword='" + password + "'";
						//------prepared statement
						//String searchQuery = "select * from user where username=? and password=?";
						
						// used to trace the process
						System.out.println("in AdminLoginBean.login");
						System.out.println("Your user name is " + username);
						System.out.println("Your password is " + password);
						System.out.println("Query: " + searchQuery);
						
						try {
							// connect to DB
							connection = ConnectionManager.getConnection();
							statement = connection.prepareStatement(searchQuery);
							//----prepared statement
							//PreparedStatement stmt = currentCon.prepareStatement(searchQuery);
					            //stmt.setString(1, username);
					            //stmt.setString(2, password);

					resultSet = statement.executeQuery();
					boolean more = resultSet.next();

					// If user does not exist
					if (!more) {
						System.out.println("Admin not registered");
						admin.setValid(false);
					}
					// If user exists
					else if (more) {
						int id = resultSet.getInt("Adminid");

						System.out.println("Welcome " + username);
						admin.setAdminid(id);
						admin.setValid(true);
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
				return admin;
			}

	public static List<AdminLoginBean> getAll() {

		// Preparing some objects/variable
		List<AdminLoginBean> supplierList = new LinkedList<>();
		String sql = "SELECT * FROM admin ORDER BY Adminid;";

		Statement statement = null;

		// Trace process
		System.out.println("in AdminLoginBean.getAll");

		try {
			// Connect to DB
			connection = ConnectionManager.getConnection();

			statement = connection.createStatement();

			resultSet = statement.executeQuery(sql);

			// Iterate over the ResultSet, add row into object and object into list
			while (resultSet.next()) {
				AdminLoginBean supplier = new AdminLoginBean();
				supplier.setAdminid(resultSet.getInt("Adminid"));
				supplier.setUsername(resultSet.getString("adminname"));
				
		

				supplierList.add(supplier);
			}
		} catch (Exception ex) {
			System.out.println("Error in AdminLoginDao.getAll: " + ex);
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
		return supplierList;
	}

}
