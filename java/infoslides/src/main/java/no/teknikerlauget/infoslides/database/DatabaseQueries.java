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

	public List<Slide> GetSlideshow() {
		String currentClockTime = GetCurrentClockTime();
		String currentDate = GetCurrentDate();
		List<Slide> slides = new ArrayList<Slide>();
		//TODO
		return slides;
	}

	public void NewSlide(Slide slide) {
		//TODO
	}

	public void EditSlide(Slide slide){
		UpdateSlideTags(slide);
		//TODO
	}

	public void DeleteSlide(Slide slide) {
		//TODO
	}

	/**
	 * Deletes all elements in the table slidetags referring to the slide given
	 * @param slide
	 */
	private void DeleteOldTagReferences(Slide slide) {
		//TODO
	}

	/**
	 * Links tags to the given slide in the table slidetags
	 * @param slide
	 */
	private void InsertNewTagRefences(Slide slide) {
		//TODO
	}

	private void UpdateSlideTags(Slide slide) {
		DeleteOldTagReferences(slide);
		InsertNewTagRefences(slide);
	}

	/**
	 * This method inserts the given tag as a new tag in the database
	 * @param tag this should be a tag sent from edit page with id -1
	 */
	public void NewTag(Tag tag) {
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

			InsertDays(generatedKeys.getInt(1), tag.getDays());

		}
		catch (SQLException exception) {
			exception.printStackTrace();
		}
	}

	public void DeleteTag(Tag tag) {
		//TODO
	}

	public void EditTag(Tag tag) {
		//TODO
	}

	public void InsertDays(int tagid, List<Day> days) {
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

	public void DeleteDays(int tagid) {
		//TODO
	}

	public void NewTheme(Theme theme) {
		//TODO
	}

	public void EditTheme(Theme theme) {
		//TODO
	}

	public void Delete(Theme theme) {
		//TODO
	}

	public List<Slide> GetAllSlides() {
		List<Slide> slides = new ArrayList<Slide>();
		//TODO
		return slides;
	}

	public List<Tag> GetAllTags() {
		List<Tag> tags = new ArrayList<>();
		//TODO
		return tags;
	}

	public List<Theme> GetAllThemes() {
		List<Theme> themes = new ArrayList<>();
		//TODO
		return themes;
	}

	public List<Slide> GetInfoslides(String input) {
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

	public List<Slide> GetInfoslideByID(int id) {
		List<Slide> slides = new ArrayList<Slide>();
		//TODO
		return slides;
	}

	private String GetCurrentClockTime() {
		Calendar current = new GregorianCalendar();
		return String.format("%02d", current.get(Calendar.HOUR_OF_DAY)) + ":" + current.get(Calendar.MINUTE);
	}

	private String GetCurrentDate() {
		Calendar current = new GregorianCalendar();
		return simpleDateFormat.format(current.getTime());
	}
}
