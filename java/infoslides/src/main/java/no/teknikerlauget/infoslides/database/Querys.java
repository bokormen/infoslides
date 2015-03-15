package no.teknikerlauget.infoslides.database;

import no.teknikerlauget.infoslides.data.Day;
import no.teknikerlauget.infoslides.data.Slide;
import no.teknikerlauget.infoslides.data.Tag;
import no.teknikerlauget.infoslides.data.Theme;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oeyvind on 12.06.2014.
 */
public class Querys extends DatabaseConnector {

	/**
	 * Formats the date in a way that can be stored in database as a string, and still be ordered correctly in the database when ordering on data
	 */
	public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");





	/**
	 * Initiates a databaseConnector using properties from the given path
	 *
	 * @param propertiesPath
	 */
	public Querys(File propertiesPath) {
		super(propertiesPath);
	}

	public List<Slide> GetSlideshow() {
		List<Slide> slides = new ArrayList<Slide>();
		//TODO
		return slides;
	}

	public void NewSlide(Slide slide) {
		//TODO
	}

	public void EditSlide(Slide slide){
		//TODO
		UpdateSlideTags(slide);
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

	public void NewTag(Tag tag) {
		//TODO
	}

	public void DeleteTag(Tag tag) {
		//TODO
	}

	public void EditTag(Tag tag) {
		//TODO
		UpdateTagDays(tag);
	}

	/**
	 * Deletes all elements in the table tagdays referring to the tag given
	 * @param tag
	 */
	private void DeleteOldDayReferences(Tag tag) {
		//TODO
	}

	/**
	 * Links tags to the given stag in the table tagdays
	 * @param tag
	 */
	private void InsertNewDayRefences(Tag tag) {
		//TODO
	}

	private void UpdateTagDays(Tag tag) {
		DeleteOldDayReferences(tag);
		InsertNewDayRefences(tag);
	}

	public void NewDay(Day day) {
		//TODO
	}

	public void EditDay(Day day) {
		//TODO
	}

	public void DeleteDay(Day day) {
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
}
