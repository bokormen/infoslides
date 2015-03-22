package no.teknikerlauget.infoslides.database;

import no.teknikerlauget.infoslides.data.Day;
import no.teknikerlauget.infoslides.data.Slide;
import no.teknikerlauget.infoslides.data.Tag;
import no.teknikerlauget.infoslides.data.Theme;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Oeyvind on 12.06.2014.
 */
public class DatabaseQueries extends DatabaseConnector {

	/**
	 * Formats the date in a way that can be stored in database as a string, and still be ordered correctly in the database when ordering on data
	 */
	public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");





	/**
	 * Initiates a databaseConnector using properties from the given path
	 *
	 * @param propertiesPath
	 */
	public DatabaseQueries(File propertiesPath) {
		super(propertiesPath);
	}

	public List<Slide> getSlideshow() {
		String currentClockTime = getCurrentClockTime();
		String currentDate = getCurrentDate();
		List<Slide> slides = new ArrayList<Slide>();
		//TODO
		return slides;
	}

	public void newSlide(Slide slide) {
		//TODO
	}

	public void editSlide(Slide slide){
		updateSlideTags(slide);
		//TODO
	}

	public void deleteSlide(Slide slide) {
		//TODO
	}

	/**
	 * Deletes all elements in the table slidetags referring to the slide given
	 * @param slide
	 */
	private void deleteOldTagReferences(Slide slide) {
		//TODO
	}

	/**
	 * Links tags to the given slide in the table slidetags
	 * @param slide
	 */
	private void insertNewTagRefences(Slide slide) {
		//TODO
	}

	private void updateSlideTags(Slide slide) {
		deleteOldTagReferences(slide);
		insertNewTagRefences(slide);
	}

	/**
	 * This method inserts the given tag as a new tag in the database
	 * @param tag this should be a tag sent from edit page with id -1
	 */
	public void newTag(Tag tag) {
		try {

			String line = "INSERT INTO `Tags` (`Tag`, `Startdate`, `Enddate`, `Repeatweeks`) VALUES " + "(?, ?, ?, ?)";

			PreparedStatement preparedStatement = connection.prepareStatement(line, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, tag.getName());
			preparedStatement.setString(2, tag.getStartDate());
			preparedStatement.setString(3, tag.getEndDate());
			preparedStatement.setString(4, tag.getRepeat().name());

			preparedStatement.executeUpdate();

			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			generatedKeys.next();

			insertDays(generatedKeys.getInt(1), tag.getDays());

		}
		catch (SQLException exception) {
			exception.printStackTrace();
		}
	}

	public void deleteTag(Tag tag) {
		//TODO
	}

	public void editTag(Tag tag) {
		//TODO
	}

	public void insertDays(int tagid, List<Day> days) {
		//TODO
		try {

			String line = "INSERT INTO `Days` (`Dayname`, `Starttime`, `Endtime`, `Tagid`) VALUES " + "(?, ?, ?, ?)";

			PreparedStatement preparedStatement = connection.prepareStatement(line);
			for (Day day : days) {
				preparedStatement.setInt(1, day.getDay());
				preparedStatement.setString(2, day.getStartTime());
				preparedStatement.setString(3, day.getEndTime());
				preparedStatement.setInt(4, tagid);
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();
		}
		catch (SQLException exception) {
			exception.printStackTrace();
		}
	}

	public void deleteDays(int tagid) {
		//TODO
	}

	public void newTheme(Theme theme) {
		//TODO
	}

	public void editTheme(Theme theme) {
		//TODO
	}

	public void deleteTheme(Theme theme) {
		//TODO
	}

	public List<Slide> getAllSlides() {
		List<Slide> slides = new ArrayList<Slide>();
		//TODO
		return slides;
	}

	public List<Tag> getAllTags() {
		List<Tag> tags = new ArrayList<>();
		//TODO
		return tags;
	}

	public List<Theme> getAllThemes() {
		List<Theme> themes = new ArrayList<>();
		//TODO
		return themes;
	}

	public List<Slide> getInfoslides(String input) {
		List<Slide> slides = new ArrayList<Slide>();
		try {
			String query = "SELECT T.TopicId FROM Topics AS T WHERE T.Topic = ?;";

			PreparedStatement preparedStatement = null;
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, input);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				slides.add(null);
			}

		}
		catch (SQLException exception) {
			exception.printStackTrace();
		}
		return slides;
	}

	public List<Slide> getInfoslideByID(int id) {
		List<Slide> slides = new ArrayList<Slide>();
		//TODO
		return slides;
	}

	private String getCurrentClockTime() {
		Calendar current = new GregorianCalendar();
		return String.format("%02d", current.get(Calendar.HOUR_OF_DAY)) + ":" + current.get(Calendar.MINUTE);
	}

	private String getCurrentDate() {
		Calendar current = new GregorianCalendar();
		return simpleDateFormat.format(current.getTime());
	}
}
