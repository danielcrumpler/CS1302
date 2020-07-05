package edu.westga.cs1302.casino.test.blackjack;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.casino.game.Blackjack;
import edu.westga.cs1302.casino.model.Card;
import edu.westga.cs1302.casino.model.Rank;
import edu.westga.cs1302.casino.model.Suit;

class TestHit {

	@Test
	void testHitBust() {
		Blackjack game = new Blackjack();
		
		game.getHumanPlayer().addCard(new Card(Rank.ACE, Suit.DIAMONDS));
		game.getHumanPlayer().addCard(new Card(Rank.QUEEN, Suit.CLUBS));
		
		assertEquals( false, game.hit());
	}

}
