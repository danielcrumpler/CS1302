package edu.westga.cs1302.casino.model;

/**
 * The Card class.
 * 
 * @author CS 1302
 *
 */
public class Card implements Comparable<Card> {

	private Rank rank;
	private Suit suit;

	/**
	 * Creates a playing card with the specified rank and suit.
	 * 
	 * @precondition valid rank and suit
	 * @postcondition getValue() == value && getSuit() == suit
	 * @param rank the rank of this card
	 * @param suit the suit of this card
	 */
	public Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}

	/**
	 * Returns the rank of this card.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the rank
	 */
	public int getRank() {
		return this.rank.getValue();
	}

	/**
	 * Returns the suit of this card.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the suit
	 */
	public String getSuit() {
		return this.suit.toString();
	}

	/**
	 * Returns the numeric value on the card.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the value
	 */
	public int getValue() {
		return this.rank.getValue();
	}
	
	/**
	 * Returns the numeric value on the suit.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the value
	 */
	public int getSuitValue() {
		return this.suit.getValue();
	}

	@Override
	public int compareTo(Card card1) {
		if (this.getRank() > card1.getRank()) {
			return 1;
		} else if (this.getRank() < card1.getRank()) {
			return -1;
		} else {
			return 0;
		}
	}
}
