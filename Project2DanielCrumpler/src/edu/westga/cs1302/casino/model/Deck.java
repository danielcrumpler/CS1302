package edu.westga.cs1302.casino.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import edu.westga.cs1302.casino.util.ExceptionMessages;

/**
 * This class represents a deck of playing cards.
 * 
 * @author CS 1302
 */
public class Deck implements Iterable<Card> {

	private ArrayList<Card> cards;

	/**
	 * Constructs a new deck of cards.
	 * 
	 * @precondition none
	 * @postcondition the deck is instantiated && size() == 52
	 */
	public Deck() {
		this.cards = new ArrayList<Card>();
		for (Rank rank : Rank.values()) {
			for (Suit suit : Suit.values()) {
				this.cards.add(new Card(rank, suit));
			}
		}
	}

	/**
	 * Returns the size of this deck.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the size of this deck
	 */
	public int size() {
		return this.cards.size();
	}

	/**
	 * Returns the card at the top of the deck.
	 * 
	 * @precondition size() > 0
	 * @postcondition getCard(size()) == null && cards ==
	 *                size()@prev - 1
	 * @return card at top of deck
	 * @throws IAE with message EMPTY_DECK
	 */
	public Card draw() {
		if (this.cards.isEmpty()) {
			throw new IllegalArgumentException(ExceptionMessages.EMPTY_DECK);
		}
		Card card = this.cards.get(this.cards.size() - 1);
		this.cards.remove(card);
		return card;
	}

	/**
	 * Shuffles the deck of cards.
	 * 
	 * @precondition none
	 * @postcondition the deck is shuffled using Knuth's algorithm
	 */
	public void shuffle() {
		Collections.shuffle(this.cards);
	}

	@Override
	public Iterator<Card> iterator() {
		return this.cards.iterator();
	}
}
