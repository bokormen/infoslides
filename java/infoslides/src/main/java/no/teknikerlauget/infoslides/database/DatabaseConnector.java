package no.teknikerlauget.infoslides.database;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Oeyvind on 12.06.2014.
 */
public class DatabaseConnector {

	static {
		new File("Resources").mkdirs();
	}

	/**
	 * Is used to handle the connection to the chosen database
	 */
	public static Connection connection;

	private static String folderName_ = "Resources";

	/**
	 * some functions only need to be run once, this variable is used avoid running the more than once
	 */
	private static boolean isFirstRun_ = true;

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

			if (isFirstRun_) {
				readDatabaseSettingsFile();
			}

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

	/**
	 * This is meant to be called if there doesn't exist a settings file for the database, of if there is something wrong with the existing one
	 * It delete the existing setting file if it exist, and create a new settings file with default values
	 */
	protected static void createDatabaseSettingsFile() {
		try {
			File settingsFile = new File(folderName_ + "/DBSettings.ini");
			settingsFile.delete();
			File settingsNewFile = new File(folderName_ + "/DBSettings.ini");
			settingsNewFile.createNewFile();

			FileWriter fileWriter = new FileWriter(settingsNewFile);
			BufferedWriter bufferedWriter  = new BufferedWriter(fileWriter);

			String comments = "#Comments: no empty line is tolerated, unless it is a value that is empty\n#lines starting with # is ignored\n#this file is expected to contain six values, one false/true, and five strings" +
					"\n#\n#\n";

			String defaultUser = "\n#Username used to connect to the server\ncache";
			String defaultPW = "\n#Password used to connect to the server\nuser";

			String defaultURL = "\n#URL this is expected to to be localhost, ip like 192.168.0.111, or something like ntnu.no, nrk.no, mysql.ntnu.no\nlocalhost";
			String defaultPort = "\n#Port default SQL port is 3306, this is expected to be empty or the port number in use\n3306";
			String defaultDBName = "\n#Databse name, this is the name of the database in use, if provided sql script is used, this is Cache\nCache";

			String endOfFile = "\n#This is the end of this file, if password is left blank, this line must exist";

			bufferedWriter.write(comments + defaultURL + defaultPort + defaultDBName + defaultUser + defaultPW + endOfFile);

			bufferedWriter.flush();
			bufferedWriter.close();
		}
		catch (IOException exception) {
			exception.printStackTrace();
		}

	}

	/**
	 * This reads the database settings file, if it doesn't exist, or is corrupted, it wil create a new default settings file
	 * It reads the different settings and stores the values in the correct variables in this class
	 */
	private static void readDatabaseSettingsFile() {

		try {
			File file = new File(folderName_ + "/DBSettings.ini");

			if (!file.exists()) {
				createDatabaseSettingsFile();
			}
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader  = new BufferedReader(fileReader);

			int noOfValues = 0;
			String string = bufferedReader.readLine();

			while (string != null) {
				if (string.isEmpty()){
					setValue(noOfValues, "");
					noOfValues++;
				}
				else if (string.charAt(0) != '#') {
					setValue(noOfValues, string);
					noOfValues++;
				}
				string = bufferedReader.readLine();
			}

			bufferedReader.close();

			if (noOfValues != 5) {
				createDatabaseSettingsFile();
				readDatabaseSettingsFile();
			}
		}
		catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * This stores the values from a setting file in the correct variables in this class
	 * @param i identifies which variable to set the value to
	 * @param value this contains the value to be set
	 */
	private static void setValue(int i, String value) {
		switch (i) {
			case 0:
				url_ = value;
				break;
			case 1:
				if (value.isEmpty()) {
					port_ = value;
				}
				else {
					if (value.charAt(0)==':') { //early versions demanded that the port value must be a : followed by the port number, newer version allows for just the port number
						port_=value;
					}
					else {
						port_=":"+value;
					}
				}
				break;
			case 2:
				datebaseName_ = value;
				break;
			case 3:
				user_ = value;
				break;
			case 4:
				password_ = value;
				break;
			default:
				return;
		}
	}
}
