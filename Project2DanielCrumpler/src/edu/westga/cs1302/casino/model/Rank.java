package edu.westga.cs1302.casino.model;

/**
 * The enum class Rank that gives faces their value
 * 
 * @author Daniel Crumpler
 *
 */
public enum Rank {
	ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(12), QUEEN(13),
	KING(14);

	private final int value;

	/**
	 * Creates a value for the type face of the card
	 * 
	 * @precondition none
	 * @postcondition Card was created with given value
	 * @param value face value on the card
	 */
	Rank(int value) {
		this.value = value;
	}

	/**
	 * Returns the value associated with face
	 * 
	 * @return the value of the card
	 */
	public int getValue() {
		return this.value;
	}
}
