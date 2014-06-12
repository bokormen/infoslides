package no.teknikerlauget.infoslides.database;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Takes care of the connection between the server and the database
 *
 * @author Oeyvind
 * @author kryel
 */
public class DatabaseConnector {

	/**
	 * Is used to handle the connection to the chosen database
	 */
	public Connection connection;

	/**
	 * This variable exist to make it easy to change where settings file(s) is stored
	 */
	private String folderName_ = "Resources";

	/**
	 * some functions only need to be run once, this variable is used avoid running the more than once
	 */
	private boolean isFirstRun_ = true;

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

	/**
	 * Initiates a databaseConnector using properties from the given path
	 *
	 * @param propertiesPath Path to the properties file
	 */
	public DatabaseConnector(File propertiesPath) {
		Properties properties = findProperties(propertiesPath);
		user_ = properties.getProperty("user");
		password_ = properties.getProperty("password");
		url_ = properties.getProperty("url");
		port_ = properties.getProperty("port");
		databaseName_ = properties.getProperty("databaseName");
	}

	/**
	 * Reads properties from file. Makes new file if it does not currently exist.
	 *
	 * @param propertiesPath Path to the properties file
	 * @return properties object
	 */
	private Properties findProperties(File propertiesPath) {
		if (!propertiesPath.exists()) {
			try {
				propertiesPath.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();  // TODO
			}
			createDefaultPropertiesFile(propertiesPath);
		}
		Properties properties = readProperties(propertiesPath);
		if (isMalformedPropertiesFile(properties)) {
			createDefaultPropertiesFile(propertiesPath);
		}
		return properties;
	}

	/**
	 * Checks whether or not a file contains the following properties:
	 * -user
	 * -password
	 * -port
	 * -databaseName
	 *
	 * @param properties file to check
	 * @return true if the file is malformed
	 */
	private boolean isMalformedPropertiesFile(Properties properties) {
		List<String> propertyNames = Arrays.asList("user", "password", "url", "port", "databaseName");
		return !properties.stringPropertyNames().containsAll(propertyNames);
	}

	/**
	 * Reads properties from file and returns a properties object
	 *
	 * @param path Where the properties file is located
	 * @return properties object from the given path
	 */
	private Properties readProperties(File path) {
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}

	/**
	 * Saves the current properties to a file at the given path
	 *
	 * @param path where to store the properties file
	 */
	private void saveCurrentProperties(File path) {
		Properties properties = new Properties();
		properties.setProperty("user", user_);
		properties.setProperty("password", password_);
		properties.setProperty("url", url_);
		properties.setProperty("port", port_);
		properties.setProperty("databaseName", databaseName_);
		saveProperties(path, properties);
	}

	/**
	 * Creates and saves a default properties file at the given path
	 *
	 * @param path Where the properties file is located
	 */
	private void createDefaultPropertiesFile(File path) {
		Properties properties = new Properties();
		properties.setProperty("user", "");
		properties.setProperty("password", "");
		properties.setProperty("url", "");
		properties.setProperty("port", "");
		properties.setProperty("databaseName", "");
		saveProperties(path, properties);
	}

	/**
	 * Saves a given set of properties to a file at the given path
	 *
	 * @param path       Where the properties file is located
	 * @param properties The set of properties that should be saved
	 */
	private void saveProperties(File path, Properties properties) {
		try {
			properties.store(new FileOutputStream(path), "");
		} catch (IOException e) {
			e.printStackTrace();  // TODO
		}
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
