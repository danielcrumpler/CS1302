package edu.westga.cs1302.casino.test.blackjack;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.casino.game.Blackjack;
import edu.westga.cs1302.casino.model.Card;
import edu.westga.cs1302.casino.model.Rank;
import edu.westga.cs1302.casino.model.Suit;

/**
 * Tests the correct functionality of Blackjack.
 * 
 * @author CS 1302
 */
public class TestStand {

	@Test
	public void testStandOffAtNatual21() {
		Blackjack game = new Blackjack();

		game.getHumanPlayer().addCard(new Card(Rank.ACE, Suit.CLUBS));
		game.getHumanPlayer().addCard(new Card(Rank.JACK, Suit.CLUBS));

		game.getDealer().addCard(new Card(Rank.ACE, Suit.DIAMONDS));
		game.getDealer().addCard(new Card(Rank.QUEEN, Suit.DIAMONDS));

		game.stand();

		assertAll(() -> assertEquals("Stand-off at Natural 21.", game.getMessage()),
				() -> assertEquals(21, game.getHumanHandScore()),
				() -> assertEquals(21, game.getDealerHandScore()),
				() -> assertEquals(1, game.getTies()), 
				() -> assertEquals(0, game.getHumanWins()),
				() -> assertEquals(0, game.getDealerWins()));
	}
	
	@Test
	public void testRegular21Standoff() {
		Blackjack game = new Blackjack();

		game.getHumanPlayer().addCard(new Card(Rank.ACE, Suit.CLUBS));
		game.getHumanPlayer().addCard(new Card(Rank.ACE, Suit.DIAMONDS));
		game.getHumanPlayer().addCard(new Card(Rank.JACK, Suit.CLUBS));
		game.getHumanPlayer().addCard(new Card(Rank.NINE, Suit.HEARTS));

		game.getDealer().addCard(new Card(Rank.TEN, Suit.DIAMONDS));
		game.getDealer().addCard(new Card(Rank.NINE, Suit.DIAMONDS));
		game.getDealer().addCard(new Card(Rank.TWO, Suit.DIAMONDS));

		game.stand();

		assertAll(() -> assertEquals("Stand-off.", game.getMessage()),
				() -> assertEquals(21, game.getHumanHandScore()),
				() -> assertEquals(21, game.getDealerHandScore()),
				() -> assertEquals(1, game.getTies()), 
				() -> assertEquals(0, game.getHumanWins()),
				() -> assertEquals(0, game.getDealerWins()));
	}


	@Test
	public void testOnlyPlayerHasNatural21() {
		Blackjack game = new Blackjack();

		game.getHumanPlayer().addCard(new Card(Rank.ACE, Suit.CLUBS));
		game.getHumanPlayer().addCard(new Card(Rank.JACK, Suit.CLUBS));

		game.getDealer().addCard(new Card(Rank.FOUR, Suit.DIAMONDS));
		game.getDealer().addCard(new Card(Rank.ACE, Suit.DIAMONDS));
		game.getDealer().addCard(new Card(Rank.SIX, Suit.DIAMONDS));

		game.stand();

		assertAll(() -> assertEquals("Natural 21 - you win 2 x pot!", game.getMessage()),
				() -> assertEquals(21, game.getHumanHandScore()),
				() -> assertEquals(21, game.getDealerHandScore()),
				() -> assertEquals(0, game.getTies()), 
				() -> assertEquals(1, game.getHumanWins()),
				() -> assertEquals(0, game.getDealerWins()));
	}
	
	@Test
	public void testDealerWinsWithNatural21() {
		Blackjack game = new Blackjack();

		game.getHumanPlayer().addCard(new Card(Rank.TEN, Suit.DIAMONDS));
		game.getHumanPlayer().addCard(new Card(Rank.NINE, Suit.DIAMONDS));
		game.getHumanPlayer().addCard(new Card(Rank.TWO, Suit.DIAMONDS));
		
		game.getDealer().addCard(new Card(Rank.JACK, Suit.CLUBS));
		game.getDealer().addCard(new Card(Rank.ACE, Suit.DIAMONDS));

		game.stand();

		assertAll(() -> assertEquals("Dealer wins with Natural 21.", game.getMessage()),
				() -> assertEquals(21, game.getHumanHandScore()),
				() -> assertEquals(21, game.getDealerHandScore()),
				() -> assertEquals(0, game.getTies()), 
				() -> assertEquals(0, game.getHumanWins()),
				() -> assertEquals(1, game.getDealerWins()));
	}
	
	@Test
	public void testDealerWinsWithNatural21PlayerStandsEarly() {
		Blackjack game = new Blackjack();

		game.getHumanPlayer().addCard(new Card(Rank.TEN, Suit.DIAMONDS));
		game.getHumanPlayer().addCard(new Card(Rank.NINE, Suit.DIAMONDS));
		
		game.getDealer().addCard(new Card(Rank.ACE, Suit.DIAMONDS));
		game.getDealer().addCard(new Card(Rank.QUEEN, Suit.CLUBS));

		game.stand();

		assertAll(() -> assertEquals("Dealer wins with Natural 21.", game.getMessage()),
				() -> assertEquals(19, game.getHumanHandScore()),
				() -> assertEquals(21, game.getDealerHandScore()),
				() -> assertEquals(0, game.getTies()), 
				() -> assertEquals(0, game.getHumanWins()),
				() -> assertEquals(1, game.getDealerWins()));
	}
	
	
	@Test
	public void testDealerWinsWithNatural21PlayerBusts() {
		Blackjack game = new Blackjack();

		game.getHumanPlayer().addCard(new Card(Rank.TEN, Suit.DIAMONDS));
		game.getHumanPlayer().addCard(new Card(Rank.NINE, Suit.DIAMONDS));
		game.getHumanPlayer().addCard(new Card(Rank.THREE, Suit.DIAMONDS));
		
		game.getDealer().addCard(new Card(Rank.ACE, Suit.DIAMONDS));
		game.getDealer().addCard(new Card(Rank.TEN, Suit.CLUBS));

		game.stand();

		assertAll(
				//() -> assertEquals("Bust - you lose.", game.getMessage()),
				() -> assertEquals(22, game.getHumanHandScore()),
				() -> assertEquals(21, game.getDealerHandScore()),
				() -> assertEquals(0, game.getTies()), 
				() -> assertEquals(0, game.getHumanWins()),
				() -> assertEquals(1, game.getDealerWins()));
	}
	
	@Test
	public void testPlayerWinsWith21() {
		Blackjack game = new Blackjack();

		game.getHumanPlayer().addCard(new Card(Rank.SIX, Suit.DIAMONDS));
		game.getHumanPlayer().addCard(new Card(Rank.FIVE, Suit.DIAMONDS));
		game.getHumanPlayer().addCard(new Card(Rank.TEN, Suit.DIAMONDS));
		
		game.getDealer().addCard(new Card(Rank.ACE, Suit.DIAMONDS));
		game.getDealer().addCard(new Card(Rank.FOUR, Suit.CLUBS));
		game.getDealer().addCard(new Card(Rank.THREE, Suit.CLUBS));

		game.stand();

		assertAll(() -> assertEquals("You win!", game.getMessage()),
				() -> assertEquals(21, game.getHumanHandScore()),
				() -> assertEquals(18, game.getDealerHandScore()),
				() -> assertEquals(0, game.getTies()), 
				() -> assertEquals(1, game.getHumanWins()),
				() -> assertEquals(0, game.getDealerWins()));
	}
	
	
	@Test
	public void testDealerBusts() {
		Blackjack game = new Blackjack();

		game.getHumanPlayer().addCard(new Card(Rank.SIX, Suit.DIAMONDS));
		game.getHumanPlayer().addCard(new Card(Rank.FIVE, Suit.DIAMONDS));
		game.getHumanPlayer().addCard(new Card(Rank.NINE, Suit.DIAMONDS));
		
		game.getDealer().addCard(new Card(Rank.QUEEN, Suit.DIAMONDS));
		game.getDealer().addCard(new Card(Rank.FOUR, Suit.CLUBS));
		game.getDealer().addCard(new Card(Rank.TWO, Suit.CLUBS));
		game.getDealer().addCard(new Card(Rank.NINE, Suit.CLUBS));
		
		game.stand();

		assertAll(() -> assertEquals("Dealer busts.", game.getMessage()),
				() -> assertEquals(0, game.getTies()), 
				() -> assertEquals(1, game.getHumanWins()),
				() -> assertEquals(0, game.getDealerWins()));
	}
	
	@Test
	public void testPlayerWinsLessThan21() {
		Blackjack game = new Blackjack();

		game.getHumanPlayer().addCard(new Card(Rank.JACK, Suit.DIAMONDS));
		game.getHumanPlayer().addCard(new Card(Rank.KING, Suit.SPADES));
		
		game.getDealer().addCard(new Card(Rank.QUEEN, Suit.DIAMONDS));
		game.getDealer().addCard(new Card(Rank.FOUR, Suit.CLUBS));
		game.getDealer().addCard(new Card(Rank.TWO, Suit.HEARTS));
		game.getDealer().addCard(new Card(Rank.THREE, Suit.HEARTS));
		
		game.stand();

		assertAll(() -> assertEquals("You win.", game.getMessage()),
				() -> assertEquals(20, game.getHumanHandScore()),
				() -> assertEquals(19, game.getDealerHandScore()),
				() -> assertEquals(0, game.getTies()), 
				() -> assertEquals(1, game.getHumanWins()),
				() -> assertEquals(0, game.getDealerWins()));
	}

	@Test
	public void testTieATLessThan21() {
		Blackjack game = new Blackjack();

		game.getHumanPlayer().addCard(new Card(Rank.JACK, Suit.HEARTS));
		game.getHumanPlayer().addCard(new Card(Rank.FIVE, Suit.DIAMONDS));
		game.getHumanPlayer().addCard(new Card(Rank.THREE, Suit.HEARTS));

		game.getDealer().addCard(new Card(Rank.TEN, Suit.DIAMONDS));
		game.getDealer().addCard(new Card(Rank.TWO, Suit.CLUBS));
		game.getDealer().addCard(new Card(Rank.THREE, Suit.CLUBS));
		game.getDealer().addCard(new Card(Rank.TWO, Suit.SPADES));
		game.getDealer().addCard(new Card(Rank.ACE, Suit.SPADES));

		game.stand();

		assertAll(() -> assertEquals("Tie.", game.getMessage()),
				() -> assertEquals(18, game.getHumanHandScore()),
				() -> assertEquals(18, game.getDealerHandScore()),
				() -> assertEquals(1, game.getTies()), 
				() -> assertEquals(0, game.getHumanWins()),
				() -> assertEquals(0, game.getDealerWins()));
	}
	
	
	@Test
	public void testPlayerWinsByBeingCloserTo21() {
		Blackjack game = new Blackjack();

		game.getHumanPlayer().addCard(new Card(Rank.JACK, Suit.HEARTS));
		game.getHumanPlayer().addCard(new Card(Rank.FIVE, Suit.DIAMONDS));
		game.getHumanPlayer().addCard(new Card(Rank.FOUR, Suit.HEARTS));

		game.getDealer().addCard(new Card(Rank.ACE, Suit.DIAMONDS));
		game.getDealer().addCard(new Card(Rank.SEVEN, Suit.CLUBS));

		game.stand();

		assertAll(() -> assertEquals("You win.", game.getMessage()),
				() -> assertEquals(19, game.getHumanHandScore()),
				() -> assertEquals(18, game.getDealerHandScore()),
				() -> assertEquals(0, game.getTies()), 
				() -> assertEquals(1, game.getHumanWins()),
				() -> assertEquals(0, game.getDealerWins()));
	}
	
	@Test
	public void testTieLessThan21() {
		Blackjack game = new Blackjack();

		game.getHumanPlayer().addCard(new Card(Rank.TEN, Suit.HEARTS));
		game.getHumanPlayer().addCard(new Card(Rank.FIVE, Suit.DIAMONDS));
		game.getHumanPlayer().addCard(new Card(Rank.FIVE, Suit.HEARTS));

		game.getDealer().addCard(new Card(Rank.SIX, Suit.DIAMONDS));
		game.getDealer().addCard(new Card(Rank.ACE, Suit.CLUBS));
		game.getDealer().addCard(new Card(Rank.ACE, Suit.CLUBS));
		game.getDealer().addCard(new Card(Rank.ACE, Suit.CLUBS));
		game.getDealer().addCard(new Card(Rank.ACE, Suit.CLUBS));

		game.stand();

		assertAll(() -> assertEquals("Tie.", game.getMessage()),
				() -> assertEquals(20, game.getHumanHandScore()),
				() -> assertEquals(20, game.getDealerHandScore()),
				() -> assertEquals(1, game.getTies()), 
				() -> assertEquals(0, game.getHumanWins()),
				() -> assertEquals(0, game.getDealerWins()));
	}
}
