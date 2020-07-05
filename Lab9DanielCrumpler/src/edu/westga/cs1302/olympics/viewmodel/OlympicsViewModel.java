package edu.westga.cs1302.olympics.viewmodel;

import edu.westga.cs1302.olympics.model.Location;
import edu.westga.cs1302.olympics.model.OlympicsGames;
import edu.westga.cs1302.olympics.model.OlympicsKey;
import edu.westga.cs1302.olympics.model.OlympicsType;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

/**
 * The Class OlympicsViewModel.
 * 
 * @author CS1302
 */
public class OlympicsViewModel {

	private final StringProperty yearProperty;
	private final StringProperty cityProperty;
	private final StringProperty countryProperty;
	private final ObjectProperty<OlympicsType> typeProperty;
	private final ListProperty<OlympicsKey> listProperty;
	private OlympicsGames games;

	/**
	 * Instantiates a new password manager gui view model.
	 */
	public OlympicsViewModel() {
		this.yearProperty = new SimpleStringProperty();
		this.cityProperty = new SimpleStringProperty();
		this.countryProperty = new SimpleStringProperty();
		this.typeProperty = new SimpleObjectProperty<OlympicsType>();
		this.games = new OlympicsGames();
		this.listProperty = new SimpleListProperty<OlympicsKey>(FXCollections.observableArrayList(this.games.keys()));
	}

	/**
	 * Gets the year property.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the year property
	 */
	public StringProperty yearProperty() {
		return this.yearProperty;
	}

	/**
	 * Returns the list property.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the student list property
	 */
	public ListProperty<OlympicsKey> listProperty() {
		return this.listProperty;
	}

	/**
	 * Gets the city property.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the city property
	 */
	public StringProperty cityProperty() {
		return this.cityProperty;
	}

	/**
	 * Gets the country property.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the country property
	 */
	public StringProperty countryProperty() {
		return this.countryProperty;
	}

	/**
	 * Gets the type property.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the type property
	 */
	public ObjectProperty<OlympicsType> typeProperty() {
		return this.typeProperty;
	}

	/**
	 * Adds the olympic game.
	 *
	 * @return true, if successful; false, otherwise
	 */
	public boolean addOlympics() {
		String city = this.cityProperty.get();
		String country = this.countryProperty.get();
		Location location = null;
		location = new Location(city, country);

		OlympicsType type = this.typeProperty.get();
		int year = Integer.parseInt(this.yearProperty.get());
		OlympicsKey key = null;
		key = new OlympicsKey(type, year);

		if (this.games.add(key, location)) {
			this.clear();
			this.updateDisplay();
			return true;
		}

		return false;
	}

	/**
	 * Updates the olympic game.
	 *
	 * @return true, if successful; false, otherwise
	 */
	public boolean updateOlympics() {
		String city = this.cityProperty.get();
		String country = this.countryProperty.get();
		Location location = null;
		location = new Location(city, country);

		OlympicsType type = this.typeProperty.get();
		int year = Integer.parseInt(this.yearProperty.get());
		OlympicsKey key = null;
		key = new OlympicsKey(type, year);

		if (this.games.containsKey(key)) {
			this.games.update(key, location);
			this.clear();
			this.updateDisplay();
			return true;
		}

		return false;
	}

	/**
	 * Searches for the location of the olympic game with the specified type and
	 * year.
	 *
	 * @return location, if successful; null, otherwise
	 */
	public Location searchForOlympics() {
		OlympicsType type = this.typeProperty.get();
		int year = Integer.parseInt(this.yearProperty.get());
		OlympicsKey key = null;
		key = new OlympicsKey(type, year);

		return this.games.get(key);
	}

	/**
	 * Deletes the olympic game.
	 *
	 * @return true, if successful; false, otherwise
	 */
	public boolean deleteOlympics() {
		OlympicsType type = this.typeProperty.get();
		int year = Integer.parseInt(this.yearProperty.get());
		OlympicsKey key = null;
		key = new OlympicsKey(type, year);

		if (this.games.remove(key)) {
			this.clear();
			this.updateDisplay();
			return true;
		}

		return false;
	}

	private void updateDisplay() {
		this.listProperty.set(FXCollections.observableArrayList(this.games.keys()));
	}

	private void clear() {
		this.cityProperty.set("");
		this.countryProperty.set("");
		this.yearProperty.set("");
	}

}
