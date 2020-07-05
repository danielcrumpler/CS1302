package edu.westga.cs1302.olympics.model;

/**
 * The Enum OlympicsType
 * 
 * @author CS1302
 *
 */
public enum OlympicsType {
	SUMMER_OLYMPICS("Summer Olympics"), WINTER_OLYMPICS("Winter Olympics"), PARALYMPICS("Prarlympics"),
	YOUTH_OLYMPICS("Youth Olympics");

	private String description;

	OlympicsType(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return this.description;
	}
}
