package edu.westga.cs1302.casino.model;

/**
 * The enum class Suit
 * 
 * @author Daniel Crumpler
 *
 */
public enum Suit {
	SPADES(1), HEARTS(2), DIAMONDS(3), CLUBS(4);
	
	private final int value;

	/**
	 * Creates a value for the type of suit
	 * 
	 * @precondition none
	 * @postcondition suit is created with a value
	 * @param value value of the suit
	 */
	Suit(int value) {
		this.value = value;
	}

	/**
	 * Returns the value associated with suit
	 * 
	 * @return the value of the suit
	 */
	public int getValue() {
		return this.value;
	}
}
