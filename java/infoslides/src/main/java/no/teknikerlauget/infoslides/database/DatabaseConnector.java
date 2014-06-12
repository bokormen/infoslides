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
	public Connection connection;

	/**
	 * this contains the username used when connecting to a mysql database
	 */
	private String user_;

	/**
	 * this contains the password used when connecting to a mysql database
	 */
	private String password_;

	/**
	 * this contains part of the address used when connecting to a mysql database
	 */
	private String mysqlSpecific_ = "jdbc:mysql://";

	/**
	 * this contains part of the address used when connecting to a mysql database
	 */
	private String url_;

	/**
	 * this contains part of the address used when connecting to a mysql database
	 */
	private String port_;

	/**
	 * this contains part of the address used when connecting to a mysql database
	 */
	private String databaseName_;

	public DatabaseConnector(String user, String password, String url, String port, String datebaseName) {
		this.user_ = user;
		this.password_ = password;
		this.url_ = url;
		this.port_ = port;
		this.databaseName_ = datebaseName;
	}

	/**
	 * This will open a connection to a mysql database
	 */
	public void open() {
		try {

			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
			} catch (InstantiationException | IllegalAccessException exception) {
				exception.printStackTrace();
			}

			connection = DriverManager.getConnection(mysqlSpecific_ + url_ + port_ + "/" + databaseName_, user_, password_);
		} catch (ClassNotFoundException | SQLException exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * Ends the connection to an open database connection
	 */
	public void close() {
		try {
			if (connection != null) connection.close();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}
}
