package no.teknikerlauget.infoslides.database;

import javax.management.Query;
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
	private Settings settings;

	/**
	 * Is used to handle the connection to the chosen database
	 */
	private Connection connection;

	public DatabaseConnector(File propertiesPath) {
		this.settings = new Settings(propertiesPath);
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
			connection = DriverManager.getConnection(settings.getDatabaseUrl(), settings.getUser(), settings.getPassword());
		} catch (ClassNotFoundException | SQLException exception) {
			exception.printStackTrace();
		}
	}

	public void sendQuery(Query query) {
		// TODO
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
