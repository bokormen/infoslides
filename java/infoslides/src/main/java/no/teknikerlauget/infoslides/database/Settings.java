package no.teknikerlauget.infoslides.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * @author kryel
 */
public class Settings {
	/**
	 * this contains the username used when connecting to a mysql database
	 */
	private String user;

	/**
	 * this contains the password used when connecting to a mysql database
	 */
	private String password;

	/**
	 * this contains part of the address used when connecting to a mysql database
	 */
	private String mysqlSpecific = "jdbc:mysql://";

	/**
	 * this contains part of the address used when connecting to a mysql database
	 */
	private String url;

	/**
	 * this contains part of the address used when connecting to a mysql database
	 */
	private String port;

	/**
	 * this contains part of the address used when connecting to a mysql database
	 */
	private String databaseName;

	/**
	 * Initiates a databaseConnector using properties from the given path
	 *
	 * @param propertiesPath Path to the properties file
	 */
	public Settings(File propertiesPath) {
		Properties properties = findProperties(propertiesPath);
		user = properties.getProperty("user");
		password = properties.getProperty("password");
		url = properties.getProperty("url");
		port = properties.getProperty("port");
		databaseName = properties.getProperty("databaseName");
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
	 * Saves the current properties to a file at the given path
	 *
	 * @param path where to store the properties file
	 */
	private void saveCurrentProperties(File path) {
		Properties properties = new Properties();
		properties.setProperty("user", user);
		properties.setProperty("password", password);
		properties.setProperty("url", url);
		properties.setProperty("port", port);
		properties.setProperty("databaseName", databaseName);
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
	 * Returns the string used for connecting to a database
	 *
	 * @return A full URL to the database
	 */
	public String getDatabaseUrl() {
        String databaseUrl = mysqlSpecific + url;
        if (!port.isEmpty()) {
            databaseUrl += ":" + port;
        }
        databaseUrl +=  "/" + databaseName;

        return databaseUrl;
		// return mysqlSpecific + url + port + "/" + databaseName;
	}

	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMysqlSpecific() {
		return mysqlSpecific;
	}
	public void setMysqlSpecific(String mysqlSpecific) {
		this.mysqlSpecific = mysqlSpecific;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getDatabaseName() {
		return databaseName;
	}
	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}
}
