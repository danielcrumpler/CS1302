package edu.westga.cs1302.casino.test.deck;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import edu.westga.cs1302.casino.model.Deck;
import edu.westga.cs1302.casino.util.ExceptionMessages;

/**
 * Tests the correct functionality of Deck.
 * 
 * @author CS 1302
 */
public class TestDraw {

	@Test
	public void testOneDraw() {
		Deck deck = new Deck();
		assertEquals(52, deck.size());
		deck.draw();
		assertEquals(51, deck.size());
	}

	@Test
	public void testDrawAllCardsException() {
		Deck deck = new Deck();
		assertEquals(deck.size(), 52);
		for (int i = 0; i < 52; i++) {
			deck.draw();
		}
		assertEquals(0, deck.size());
		assertThrows(IllegalArgumentException.class, () -> deck.draw());
		IllegalArgumentException exc = assertThrows(IllegalArgumentException.class, () -> deck.draw());
		assertEquals(ExceptionMessages.EMPTY_DECK, exc.getMessage());
	}

}
