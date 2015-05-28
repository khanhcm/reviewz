package vn.com.reviewz.ultilities;

import java.sql.*;

/************************************************/
/*
 * Class Database Connector
 * Connect to database and excute query
 * */
/*************************************************/

public final class DatabaseConnector {
	// Connection variable, which is interacting with db
	private Connection connection = null;

	// Instance of singleton
	private static DatabaseConnector db = null;

	/*
	 * Singleton implementation
	 */
	public static synchronized DatabaseConnector getDbCon() {
		if (db == null) {
			db = new DatabaseConnector();
		}
		return db;
	}

	/*
	 * Constructor create connection
	 */
	private DatabaseConnector() {
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "database_name";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "";

		try {
			Class.forName(driver).newInstance();
			this.connection = DriverManager.getConnection(url + dbName,
					userName, password);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * Get Connection from database connector
	 */
	public Connection getConnection() {
		return this.connection;
	}
	
	/*
	 * Close connection
	 */
	public void closeConnection() throws SQLException {
		if(this.connection != null) {
			this.connection.close();
		}
	}

}
