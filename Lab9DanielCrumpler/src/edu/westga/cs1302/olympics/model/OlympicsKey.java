package edu.westga.cs1302.olympics.model;

import edu.westga.cs1302.olympics.resources.ExceptionMessages;

/**
 * The Class OlympicsKey.
 * 
 * @author Daniel Crumpler
 */
public class OlympicsKey {

	private OlympicsType type;
	private int year;

	/**
	 * Instantiates a new olympics key.
	 *
	 * @precondition id != null && id is not empty
	 * @postcondition get() == id
	 * 
	 * @param type the olympics type
	 * @param year the year
	 */
	public OlympicsKey(OlympicsType type, int year) {
		if (type == null) {
			throw new IllegalArgumentException(ExceptionMessages.NULL_TYPE);
		}

		if (year < 1896) {
			throw new IllegalArgumentException(ExceptionMessages.INVALID_YEAR_ENTRY);
		}

		this.type = type;
		this.year = year;
	}

	/**
	 * Gets the type of olympics.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the type
	 */
	public OlympicsType getType() {
		return this.type;
	}

	/**
	 * Sets the olympics type.
	 *
	 * @precondition none
	 * @postcondition getType() == type
	 * 
	 * @param type the olympics type
	 */
	public void setType(OlympicsType type) {
		if (type == null) {
			throw new IllegalArgumentException(ExceptionMessages.NULL_TYPE);
		}
		this.type = type;
	}

	/**
	 * Gets the year of the olympics.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the year
	 */
	public int getYear() {
		return this.year;
	}

	/**
	 * Sets the year of the olympics.
	 *
	 * @precondition none
	 * @postcondition getYear() == year
	 * 
	 * @param year the year of the olympics
	 */
	public void setYear(int year) {
		if (year < 1896) {
			throw new IllegalArgumentException(ExceptionMessages.INVALID_YEAR_ENTRY);
		}
		this.year = year;
	}

	@Override
	public boolean equals(Object key) {
		if (!(key instanceof OlympicsKey)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		String hash = this.type.toString() + this.year;
		return hash.hashCode();
	}

	@Override
	public String toString() {
		return this.type + " " + this.year;
	}
}
