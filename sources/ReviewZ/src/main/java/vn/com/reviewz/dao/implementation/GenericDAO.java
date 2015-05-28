package vn.com.reviewz.dao.implementation;

import java.sql.*;

import vn.com.reviewz.ultilities.*;

public class GenericDAO {
	// Connection variable, which is interacting with db
	protected Connection connection = null;

	// Object excute store procedure
	private CallableStatement callableStatement = null;

	public GenericDAO() {
		connection = DatabaseConnector.getDbCon().getConnection();
	}

	/*
	 * Excute procedure without any parameter procString: the string of store -
	 * procedure(include 'call' method) ex: call procedure(?, ?)
	 */
	public ResultSet excuteQuery(String procString) {
		ResultSet rs = null;
		if (connection == null) {
			return rs;
		}
		try {
			connection.setAutoCommit(false);
			callableStatement = connection.prepareCall(procString);
			rs = callableStatement.executeQuery();
			connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException ex1) {
				}
			}
		}
		return rs;
	}

	/*
	 * Excute procedure with some parameters procString: the string of store -
	 * procedure(include 'call' method) ex: call procedure(?, ?)
	 */
	public ResultSet excuteQuery(Connection conn, String procString) {
		ResultSet rs = null;
		if (connection == null) {
			return rs;
		}
		try {
			conn.setAutoCommit(false);
			callableStatement = connection.prepareCall(procString);
			rs = callableStatement.executeQuery();
			connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException ex1) {
				}
			}
		}
		return rs;
	}
	
	/*
	 * Close connection
	 */
	public void closeConnection() {
		if(callableStatement != null) {
			try {
				callableStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(connection!=null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
