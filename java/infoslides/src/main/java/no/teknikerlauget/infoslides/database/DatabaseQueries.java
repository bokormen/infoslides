package no.teknikerlauget.infoslides.database;

import no.teknikerlauget.infoslides.data.*;

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
		try {
			Repeat repeat = getWeekRepeat(Calendar.WEEK_OF_YEAR);
			//TODO make a good query
			String query = "SELECT * FROM slides;";

			PreparedStatement preparedStatement = connection.prepareStatement(query);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("Slideid");
				String title = resultSet.getString("Title");
				String text = resultSet.getString("Slidetext");
				String picture = resultSet.getString("Picture");
				Theme theme = getTheme(resultSet.getInt("Themeid"));
				List<Integer> tags = getTagsToSlide(id);
				Slide slide = new Slide(id,title,text,picture,theme,tags);
				slides.add(slide);
			}

		}
		catch (SQLException exception) {
			exception.printStackTrace();
		}
		return slides;
	}

	public int newSlide(Slide slide) {
		//TODO
		int slideId = -1;
		try {

			String line = "INSERT INTO `Slides` (`Title`, `Slidetext`, `Picture`, `Themeid`) VALUES " + "(?, ?, ?, ?)";

			PreparedStatement preparedStatement = connection.prepareStatement(line, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, slide.getTitle());
			preparedStatement.setString(2, slide.getText());
			preparedStatement.setString(3, slide.getImage());
			preparedStatement.setInt(4, slide.getTheme().getId());

			preparedStatement.executeUpdate();

			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			generatedKeys.next();

			slideId = generatedKeys.getInt(1);

			insertNewTagRefences(slideId, slide.getTagIdList());
		}
		catch (SQLException exception) {
			exception.printStackTrace();
		}
		return slideId;
	}

	public void editSlide(Slide slide){
		updateSlideTags(slide.getId(), slide.getTagIdList());
		//TODO
	}

	public void deleteSlide(Slide slide) {
		//TODO
	}

	/**
	 * Deletes all elements in the table slidetags referring to the slide given
	 * @param slideid
	 */
	private void deleteOldTagReferences(int slideid) {
		//TODO
		try {

			String line = "DELETE FROM `SlideTags` WHERE `Slideid` = ?" ;

			PreparedStatement preparedStatement = connection.prepareStatement(line);
			preparedStatement.setInt(1, slideid);

			preparedStatement.executeUpdate();
		}
		catch (SQLException exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * Links tags to the given slide in the table slidetags
	 * @param slideid
	 */
	private void insertNewTagRefences(int slideid, List<Integer> tags) {
		//TODO
		try {

			String line = "INSERT INTO `SlideTags` (`Slideid`, `Tagid`) VALUES " + "(?, ?)";

			PreparedStatement preparedStatement = connection.prepareStatement(line);
			for (int tag : tags) {
				preparedStatement.setInt(1, slideid);
				preparedStatement.setInt(2, tag);
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();
		}
		catch (SQLException exception) {
			exception.printStackTrace();
		}
	}

	private void updateSlideTags(int slideid, List<Integer> tags) {
		deleteOldTagReferences(slideid);
		insertNewTagRefences(slideid, tags);
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

	private List<Integer> getTagsToSlide(int slideId) {
		List<Integer> tags = new ArrayList<Integer>();
		//TODO should return a list of tagids of tags connected to a slide
		return tags;
	}

	public void insertDays(int tagid, List<Day> days) {
		//TODO could this somehow be integrated in the tag writhing process, to reduce the number of connections to the database?
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
		//INSERT INTO `Themes` (`Themename`, `Cssstyle`, `Description`) VALUES ("eththeme2", "En style nummer to", "en beskrivelse");
		try {

			String line = "INSERT INTO `Themes` (`Themename`, `Cssstyle`, `Description`) VALUES " + "(?, ?, ?)";

			PreparedStatement preparedStatement = connection.prepareStatement(line);
			preparedStatement.setString(1, theme.getName());
			preparedStatement.setString(2, theme.getCss());
			preparedStatement.setString(3, theme.getDescription());

			preparedStatement.executeUpdate();
		}
		catch (SQLException exception) {
			exception.printStackTrace();
		}
	}

	public void editTheme(Theme theme) {
		//TODO
		//UPDATE `Themes` SET `Themename`="eththeme2", `Cssstyle`="En style nummer tre", `Description`= "en beskrivelse" WHERE `Themename`="eththeme2"
	}

	public void deleteTheme(Theme theme) {
		//TODO
		setAffectedSlidesToDefaultTheme();
	}

	private Theme getTheme(int id) {
		Theme theme = null;
		//TODO
		return theme;
	}

	private void setAffectedSlidesToDefaultTheme() {
		//TODO
	}

	public List<Slide> getAllSlides() {
		//TODO
		List<Slide> slides = new ArrayList<Slide>();
		try {
			String query = "SELECT * FROM slides;";

			PreparedStatement preparedStatement = connection.prepareStatement(query);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("Slideid");
				String title = resultSet.getString("Title");
				String text = resultSet.getString("Slidetext");
				String picture = resultSet.getString("Picture");
				Theme theme = getTheme(resultSet.getInt("Themeid"));
				List<Integer> tags = getTagsToSlide(id);
				Slide slide = new Slide(id,title,text,picture,theme,tags);
				slides.add(slide);
			}

		}
		catch (SQLException exception) {
			exception.printStackTrace();
		}
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

	private Repeat getWeekRepeat(int weekNumber) {
			if ((weekNumber%2)==0) {
				return Repeat.EVEN_WEEKS;
			} else {
				return Repeat.ODD_WEEKS;
			}
	}

	public boolean isTagUnique() {
		boolean uniqueTag = true;

		// TODO

		return uniqueTag;
	}

	public boolean isThemeNameUnique() {
		boolean uniqueTheme = true;

		// TODO

		return uniqueTheme;
	}

	/**
	 * Truncate every table in the database
	 */
	public void emptyMysqlDatabase() {
		try {
			String line = "SET foreign_key_checks = 0;";
			PreparedStatement preparedStatement = connection.prepareStatement(line);
			preparedStatement.execute();

			line = "TRUNCATE TABLE Tags";
			preparedStatement = connection.prepareStatement(line);
			preparedStatement.execute();

			line = "TRUNCATE TABLE Themes";
			preparedStatement = connection.prepareStatement(line);
			preparedStatement.execute();

			line = "TRUNCATE TABLE Slides";
			preparedStatement = connection.prepareStatement(line);
			preparedStatement.execute();

			line = "TRUNCATE TABLE Days";
			preparedStatement = connection.prepareStatement(line);
			preparedStatement.execute();

			line = "TRUNCATE TABLE SlideTags";
			preparedStatement = connection.prepareStatement(line);
			preparedStatement.execute();

			line = "SET foreign_key_checks = 1;";
			preparedStatement = connection.prepareStatement(line);
			preparedStatement.execute();

		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}
}
