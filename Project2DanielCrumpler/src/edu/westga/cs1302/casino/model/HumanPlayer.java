package edu.westga.cs1302.casino.model;

import edu.westga.cs1302.casino.util.ExceptionMessages;

/**
 * The class HumanPlayer.
 * 
 * @author Daniel Crumpler
 */
public class HumanPlayer extends Player {

	private int money;
	
	/**
	 * Instantiates a new player with $100.
	 * 
	 * @precondition none
	 * @postcondition getMoney() == 100
	 */
	public HumanPlayer() {
		super();
		this.money = 100;
	}
	
	/**
	 * Returns this player's money.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the money
	 */
	public int getMoney() {
		return this.money;
	}
	
	/**
	 * Updates this player's money with the specified amount.
	 * 
	 * @precondition none
	 * @postcondition getMoney() == getMoney()@prev + amount
	 * @param amount the amount to set
	 */
	public void playerGets(int amount) {
		this.money += amount;
	}
	
	/**
	 * The amount of money to bet on the current game.
	 * 
	 * @precondition amount <= getMoney()
	 * @postcondition getMoney() == getMoney()@prev - amount
	 * @param amount the amount to bet
	 */
	public void betAmount(int amount) {
		if (amount > this.money || amount < 0) {
			throw new IllegalArgumentException(ExceptionMessages.INVALID_AMOUNT);
		}
		this.money -= amount;
	}

	/**
	 * Sets the player's amount of money.
	 * 
	 * @precondition money >= 0
	 * @postcondition getMoney() == money
	 * @param money to set for this player
	 */
	public void setMoney(int money) {
		if (money < 0) {
			throw new IllegalArgumentException(ExceptionMessages.INVALID_AMOUNT);
		}
		this.money = money;
	}
}
