package edu.westga.cs1302.casino.game;

import edu.westga.cs1302.casino.model.HumanPlayer;
import edu.westga.cs1302.casino.model.Player;

/**
 * The Game class.
 * 
 * @author CS 1302
 *
 */
public interface Playable {

	/**
	 * Returns the human player of this game.
	 * 
	 * @return the human player
	 */
	HumanPlayer getHumanPlayer();

	/**
	 * Returns the dealer of this game.
	 * 
	 * @return the human player
	 */
	Player getDealer();

	/**
	 * The human player asks for a card.
	 * 
	 * @precondition none
	 * @postcondition the player asks and is dealt a new card; stats are updated if
	 *                they bust, and only score is updated otherwise
	 * @return true if player can continue game, false if bust
	 */
	boolean hit();

	/**
	 * The human played the round and now it's the dealer's turn to play their round
	 * and finish the game.
	 * 
	 * @precondition player is done with their turn and score is updated
	 * @postcondition the game is over and stats are updated
	 */
	void stand();

	/**
	 * Returns a message after the game's state has changed in a meaningful way,
	 * e.g., when a player draws a card or when the game ends.
	 * 
	 * @return the message from the game
	 */
	String getMessage();

	/**
	 * Returns the pot of this game.
	 * 
	 * @return the pot
	 */
	int getPot();

	/**
	 * Sets the pot of the game.
	 * 
	 * @param pot the pot to be set for this game
	 * @precondition none
	 * @postcondition getPot() == pot
	 */
	void setPot(int pot);

	/**
	 * Returns a String representation of the game's statistics to be displayed when
	 * a new round has ended.
	 * 
	 * @return the stats
	 */
	String getStats();

	/**
	 * Returns a String representation of player's current score. It is usually
	 * called after the player has drawn a new card, which will modify their score.
	 * 
	 * @return the human player's score
	 */
	int getHumanHandScore();

	/**
	 * Returns a String representation of player's current score. It is usually
	 * called after the player has drawn a new card, which will modify their score.
	 * 
	 * @return the dealer's score
	 */
	int getDealerHandScore();

	/**
	 * Returns the money of the human player.
	 * 
	 * @precondition humanPlayer != null
	 * @postcondition none
	 * @param humanPlayer the specified human player
	 * @return the money of the human player
	 */
	int getMoneyOf(HumanPlayer humanPlayer);

	/**
	 * This method deals the initial hands to the players.
	 */
	void dealHands();

	/**
	 * This method starts a new round of this game.
	 */
	void startNewRound();
	
}
