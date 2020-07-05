package edu.westga.cs1302.olympics.model;

import edu.westga.cs1302.olympics.resources.ExceptionMessages;

/**
 * The Class Location that keeps track of a city and the country the city is
 * located in
 * 
 * @author CS1302
 */
public class Location {

	private String city;
	private String country;

	/**
	 * Instantiates a new location.
	 * 
	 * @precondition city != null and city is not empty AND country != null and
	 *               country is not empty
	 * @postcondition getCity() == city AND getCountry() == country
	 *
	 * @param city    the city
	 * @param country the country
	 */
	public Location(String city, String country) {
		if (city == null) {
			throw new IllegalArgumentException(ExceptionMessages.NULL_CITY);
		}

		if (country == null) {
			throw new IllegalArgumentException(ExceptionMessages.NULL_COUNTRY);
		}

		if (city.isEmpty()) {
			throw new IllegalArgumentException(ExceptionMessages.EMPTY_CITY);
		}

		if (country.isEmpty()) {
			throw new IllegalArgumentException(ExceptionMessages.EMPTY_COUNTRY);
		}

		this.city = city;
		this.country = country;
	}

	/**
	 * Gets the city.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the city
	 */
	public String getCity() {
		return this.city;
	}

	/**
	 * Gets the country.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the country
	 */
	public String getCountry() {
		return this.country;
	}
	
	@Override
	public String toString() {
		return this.city + ", " + this.country;
	}
}
