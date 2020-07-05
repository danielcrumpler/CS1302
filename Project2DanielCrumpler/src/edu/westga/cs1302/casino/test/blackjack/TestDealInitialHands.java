package edu.westga.cs1302.casino.test.blackjack;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.casino.game.Blackjack;

/**
 * Tests the correct functionality of Blackjack.
 * 
 * @author CS 1302
 */
public class TestDealInitialHands {

	@Test
	public void testDealInitialHandsSuccessful() {
		Blackjack game = new Blackjack();
		game.dealHands();
		assertEquals(2, game.getDealer().getNumCardsInHand());
		assertEquals(2, game.getHumanPlayer().getNumCardsInHand());
		assertEquals(0, game.getDealerWins());
		assertEquals(0, game.getHumanWins());
		assertEquals(0, game.getTies());
	}
}
