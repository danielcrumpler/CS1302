package edu.westga.cs1302.casino.game;

import java.util.ArrayList;
import java.util.Collections;

import edu.westga.cs1302.casino.model.Card;
import edu.westga.cs1302.casino.model.Deck;
import edu.westga.cs1302.casino.model.HumanPlayer;
import edu.westga.cs1302.casino.model.Player;
import edu.westga.cs1302.casino.model.SuitThenRankComparator;
import edu.westga.cs1302.casino.util.ExceptionMessages;

/**
 * The abstract class game
 * 
 * @author Crumpler
 *
 */
public abstract class Game implements Playable {

	private Deck deck;
	private Player dealer;
	private HumanPlayer humanPlayer;
	private int pot;
	private int dealerWins;
	private int humanWins;
	private int ties;
	private String message;

	/**
	 * How a game should be instantiated
	 *
	 * @precondition none
	 * @postcondition none
	 */
	public Game() {
		this.initializeStats();
		this.initializePlayers();
		this.humanPlayer.setMoney(100);
		this.startNewRound();
	}

	@Override
	public HumanPlayer getHumanPlayer() {
		return this.humanPlayer;
	}

	@Override
	public Player getDealer() {
		return this.dealer;
	}

	@Override
	public String getMessage() {
		return this.message;
	}

	/**
	 * Sets the message
	 * 
	 * @precondition none
	 * @postcondition the string as the message
	 * @param string a message
	 */
	public void setMessage(String string) {
		this.message = string;
	}

	@Override
	public int getPot() {
		return this.pot;
	}

	@Override
	public void setPot(int bet) {
		if (bet <= 0) {
			throw new IllegalArgumentException(ExceptionMessages.INVALID_AMOUNT);
		}
		if (!(bet > this.humanPlayer.getMoney())) {
			this.pot = 2 * bet;
		}
		this.humanPlayer.setMoney(this.humanPlayer.getMoney() - bet);
	}

	@Override
	public String getStats() {
		return "You: " + this.getHumanWins() + "\nDealer: " + this.getDealerWins() + "\nTies: " + this.getTies();
	}

	/**
	 * Gets the dealer's wins.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the dealerWins
	 */
	public int getDealerWins() {
		return this.dealerWins;
	}

	/**
	 * Gets the human player's wins.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the playerWins
	 */
	public int getHumanWins() {
		return this.humanWins;
	}

	/**
	 * Gets the number of ties of this.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the ties
	 */
	public int getTies() {
		return this.ties;
	}

	@Override
	public abstract int getHumanHandScore();

	@Override
	public abstract int getDealerHandScore();

	@Override
	public int getMoneyOf(HumanPlayer humanPlayer) {
		return humanPlayer.getMoney();
	}

	@Override
	public abstract void dealHands();

	@Override
	public void startNewRound() {
		this.deck = new Deck();
		this.deck.shuffle();
		this.pot = 0;
		this.dealer.emptyHand();
		this.humanPlayer.emptyHand();
	}

	private void initializeStats() {
		this.dealerWins = 0;
		this.humanWins = 0;
		this.ties = 0;
	}

	private void initializePlayers() {
		this.dealer = new Player();
		this.humanPlayer = new HumanPlayer();
	}

	/**
	 * Returns the human player's hand of cards.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the human player's cards
	 */
	public ArrayList<Card> getPlayerHand() {
		Collections.sort(this.dealer.getHand(), new SuitThenRankComparator());
		return this.humanPlayer.getHand();
	}

	/**
	 * Returns the dealer's hand of cards.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the dealer's cards
	 */
	public ArrayList<Card> getDealerHand() {
		Collections.sort(this.dealer.getHand());
		return this.dealer.getHand();
	}

	/**
	 * The game resulted in a tie.
	 * 
	 * @precondition none
	 * @postcondition getTies() == getTies()@prev + 1
	 */
	public void recordTie() {
		this.ties++;

	}

	/**
	 * The game resulted in a win for the human player.
	 * 
	 * @precondition none
	 * @postcondition getHumanWins() == getTies()@getHumanWins + 1
	 */
	public void recordHumanWin() {
		this.humanWins++;
	}

	/**
	 * The game resulted in a win for the dealer.
	 * 
	 * @precondition none
	 * @postcondition getHumanWins() == getTies()@getHumanWins + 1
	 */
	public void recordDealerWin() {
		this.dealerWins++;
	}

	/**
	 * Gets the deck of this game.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the deck
	 */
	public Deck getDeck() {
		return this.deck;
	}

	/**
	 * Gets the number of cards currently in the deck.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the number of cards currently in the deck
	 */
	public Deck getNumberCardsInDeck() {
		return this.deck;
	}

	/**
	 * The human won.
	 * 
	 * @precondition human won
	 * @postcondition pot goes to human
	 * @param amount the amount of money
	 */
	public void humanWon(int amount) {
		this.humanPlayer.playerGets(amount);
	}

	/**
	 * Adds a Human Win
	 * 
	 * @precondition none
	 * @postcondition getHumanWins() == prev+1
	 */
	public void addHumanWins() {
		this.humanWins += 1;
	}

	/**
	 * Adds a Dealer Win
	 * 
	 * @precondition none
	 * @postcondition getDealerWins() == prev+1
	 */
	public void addDealerWins() {
		this.dealerWins += 1;
	}

	/**
	 * Adds a Tie
	 * 
	 * @precondition none
	 * @postcondition getTies() == prev+1
	 */
	public void addTies() {
		this.ties += 1;
	}

	/**
	 * Sets a valid deck
	 * 
	 * @precondition none;
	 * @postconditon getDeck == deck
	 * 
	 * @param deck a deck
	 */
	public void setDeck(Deck deck) {
		this.deck = deck;
	}

}
