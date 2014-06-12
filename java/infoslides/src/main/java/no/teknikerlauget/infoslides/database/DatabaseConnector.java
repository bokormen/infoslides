package no.teknikerlauget.infoslides.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Oeyvind on 12.06.2014.
 */
public class DatabaseConnector {
	/**
	 * Is used to handle the connection to the chosen database
	 */
	public static Connection connection;

	/**
	 * this contains the username used when connecting to a mysql database
	 */
	private static String user_ = "";

	/**
	 * this contains the password used when connecting to a mysql database
	 */
	private static String password_ = "";

	/**
	 * this contains part of the address used when connecting to a mysql database
	 */
	private static String mysqlSpecific_ = "jdbc:mysql://";

	/**
	 * this contains part of the address used when connecting to a mysql database
	 */
	private static String url_ = "";

	/**
	 * this contains part of the address used when connecting to a mysql database
	 */
	private static String port_ = "";

	/**
	 * this contains part of the address used when connecting to a mysql database
	 */
	private static String datebaseName_ = "";

	/**
	 * This will open a connection to a mysql database
	 */
	public static void open() {
		try {

			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
			} catch (InstantiationException | IllegalAccessException exception) {
				exception.printStackTrace();
			}

			connection = DriverManager.getConnection(mysqlSpecific_ + url_ + port_ + "/" + datebaseName_, user_, password_);
		} catch (ClassNotFoundException | SQLException exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * Ends the connection to an open database connection
	 */
	public static void close() {
		try {
			if (connection != null) connection.close();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}
}
