package edu.westga.cs1302.olympics.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * The Class OlympicsGames.
 * 
 * @author Daniel Crumpler
 */
public class OlympicsGames {

	private Map<OlympicsKey, Location> games;

	/**
	 * Instantiates a new OlympicsGame.
	 * 
	 * @precondition none
	 * @postcondition size() == 0
	 * 
	 */
	public OlympicsGames() {
		this.games = new HashMap<OlympicsKey, Location>();
	}

	/**
	 * Clears the map
	 * 
	 * @precondition none
	 * @postcondition size() == 0
	 */
	public void clear() {
		this.games.clear();
	}

	/**
	 * Gets the Location with specified key.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param key the key
	 * @return the location with specified key; returns null if there is no student
	 *         with the id
	 */
	public Location get(OlympicsKey key) {
		return this.games.get(key);
	}

	/**
	 * Checks whether a location with the specified key exists
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param key the key
	 * @return true, if the course contains the location with the specified key
	 */
	public boolean containsKey(OlympicsKey key) {
		return this.games.containsKey(key);
	}

	/**
	 * Checks if the map is empty
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return true if map is empty
	 */
	public boolean isEmpty() {
		return this.games.isEmpty();
	}

	/**
	 * Adds the games.
	 *
	 * @precondition none
	 * @postcondition this.size() == this.size()@prev + 1
	 * 
	 * @param key      the key
	 * @param location the location
	 * 
	 * @return false if the game is already contained in this location or could not
	 *         be added; true otherwise
	 */
	public boolean add(OlympicsKey key, Location location) {
		return this.games.put(key, location) == null;
	}

	/**
	 * Updates the location
	 * 
	 * @precondition none
	 * @postcondition makes a new game with a different location removing the first
	 * 
	 * @param key      the key
	 * @param location the location
	 * 
	 */
	public void update(OlympicsKey key, Location location) {
		this.games.replace(key, location);
	}

	/**
	 * Remove the game with specified key
	 * 
	 * @precondition none
	 * @postcondition this.size() == this.size()@prev - 1
	 * 
	 * @param key the key
	 * 
	 * @return true, if game with specified key is removed.
	 */
	public boolean remove(OlympicsKey key) {
		return this.games.remove(key) != null;
	}

	/**
	 * Gets the size of the map
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the sizes of the map
	 */
	public int size() {
		return this.games.size();
	}

	/**
	 * A collection of values
	 * 
	 * @return a collection of values
	 */
	public Collection<Location> values() {
		return this.games.values();
	}

	/**
	 * A collection of keys
	 * 
	 * @return a collection of keys
	 */
	public Collection<OlympicsKey> keys() {
		return this.games.keySet();
	}
}