package main.java.com.epam.barkou.parking.dao.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import main.java.com.epam.barkou.parking.dao.exception.DAOException;

public class SQLConnection {

	public static final String JDBC_PATH = "jdbc:mysql://localhost/parking?user=root&password=root&useUnicode=true&characterEncoding=UTF-8&characterSetResults=utf8&connectionCollation=utf8_general_ci&";
	public static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	private static SQLConnection instance = null;

	private SQLConnection() {

	}

	public Connection getConnection() throws DAOException {
		Connection connection = null;

		try {
			Class.forName(DRIVER_NAME).newInstance();
			connection = DriverManager.getConnection(JDBC_PATH);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			throw new DAOException(e);
		}

		return connection;
	}

	public static SQLConnection getInstance() {
		if (instance == null) {
			instance = new SQLConnection();
		}
		return instance;
	}

}