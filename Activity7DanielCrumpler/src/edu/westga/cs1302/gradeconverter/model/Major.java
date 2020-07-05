package edu.westga.cs1302.gradeconverter.model;

/**
 * The available values of major.
 * @author csuser
 *
 */
public enum Major {
	MATH("Boyd - 3rd floor"),
	COMPUTER_SCIENCE("TLC - 1st floor"),
	BIOLOGY("Biology Building"), 
	CHEMISTRY("TLC - 2nd floor"),
	PHYSICS("Boyd - 2nd floor");
	
	private String location;
	
	/**
	 * Creates a major with location.
	 * @precondition  !null && !location.isEmpty()
	 * @postcondition getLocation() == location
	 * @param location the location to be assigned
	 */
	Major(String location) {
		this.location = location;
	}
	
	/**
	 * Returns this major's location.
	 * @precondition none
	 * @postcondition none
	 * @return the location
	 */
	public String getLocation() {
		return this.location;
	}
}
