package no.teknikerlauget.infoslides.database;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Takes care of the connection between the server and the database
 *
 * @author Oeyvind
 * @author kryel
 */
public class DatabaseConnector {
	/**
	 * Is used to store MySQL settings
	 */
	private Settings settings;

	/**
	 * Is used to handle the connection to the chosen database
	 */
	protected Connection connection;

	/**
	 * Reads settings for a MySQL connection, and stores them in settings
	 * @param propertiesPath
	 */
	public DatabaseConnector(File propertiesPath) {
		this.settings = new Settings(propertiesPath);
		open();
	}

	/**
	 * This will open a connection to a mysql database
	 */
	public void open() {
		MysqlDataSource ds = new MysqlDataSource();
		ds.setServerName(settings.getUrl());
		ds.setDatabaseName(settings.getDatabaseName());
		ds.setUser(settings.getUser());
		ds.setPassword(settings.getPassword());
		try {
			connection = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		/**
		try {

			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
			}
			catch (InstantiationException | IllegalAccessException exception) {
				exception.printStackTrace();
			}
			System.out.println(settings.getDatabaseUrl());
			connection = DriverManager.getConnection(settings.getDatabaseUrl(), settings.getUser(), settings.getPassword());
		}
		catch (ClassNotFoundException | SQLException exception) {
			exception.printStackTrace();
		}	*/
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
