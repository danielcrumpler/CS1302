package edu.westga.cs1302.casino.model;

import edu.westga.cs1302.casino.resources.UI;

/**
 * The Class Player.
 * 
 * @author CS 1302
 */
public abstract class Player implements Comparable<Player> {

	private String name;
	private int bet;
	private boolean thirsty;
	private boolean hungry;
	private boolean bored;

	/**
	 * Instantiates a new player.
	 * 
	 * @precondition name != null && name not empty && bet > 0
	 * @postcondition getName() == name && getBet() == bet && isThirsty() == false
	 *                && isHungry() == false && isBored() == false
	 * 
	 * @param name the name
	 * @param bet  the amount of the bet
	 */
	protected Player(String name, int bet) {
		if (name == null) {
			throw new IllegalArgumentException(UI.NAME_CANNOT_BE_NULL);
		}

		if (name.isEmpty()) {
			throw new IllegalArgumentException(UI.NAME_CANNOT_BE_EMPTY);
		}

		if (bet <= 0) {
			throw new IllegalArgumentException(UI.BET_MUST_BE_POSITIVE);
		}

		this.name = name;
		this.bet = bet;
		this.thirsty = false;
		this.hungry = false;
		this.bored = false;
	}

	/**
	 * Gets the name.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name.
	 * 
	 * @precondition name != null && name not empty
	 * @postcondition getName() == name
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		if (name == null) {
			throw new IllegalArgumentException(UI.NAME_CANNOT_BE_NULL);
		}

		if (name.isEmpty()) {
			throw new IllegalArgumentException(UI.NAME_CANNOT_BE_EMPTY);
		}

		this.name = name;
	}

	/**
	 * Gets the bet.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the bet
	 */
	public int getBet() {
		return this.bet;
	}

	/**
	 * Sets the bet.
	 * 
	 * @precondition bet >= 0
	 * @postcondition getBet() == bet
	 *
	 * @param bet the new bet
	 */
	public void setBet(int bet) {
		if (bet < 0) {
			throw new IllegalArgumentException(UI.BET_MUST_BE_POSITIVE);
		}

		this.bet = bet;
	}

	/**
	 * Checks if player is thirsty.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return true, if player is thirsty
	 */
	public boolean isThirsty() {
		return this.thirsty;
	}

	/**
	 * Sets whether the player is thirsty.
	 * 
	 * @precondition none
	 * @postcondition isThirsty() == thirsty
	 *
	 * @param thirsty whether player is thirsty
	 */
	public void setThirsty(boolean thirsty) {
		this.thirsty = thirsty;
	}

	/**
	 * Checks if player is hungry.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return true, if is hungry
	 */
	public boolean isHungry() {
		return this.hungry;
	}

	/**
	 * Sets whether the player is hungry.
	 *
	 * @precondition none
	 * @postcondition isHungry() == hungry
	 *
	 * @param hungry whether player is hungry
	 */
	public void setHungry(boolean hungry) {
		this.hungry = hungry;
	}

	/**
	 * Checks if player is bored.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return true, if is bored
	 */
	public boolean isBored() {
		return this.bored;
	}

	/**
	 * Sets whether the player is bored.
	 * 
	 * @precondition none
	 * @postcondition isBored() == bored
	 *
	 * @param bored whether player is bored
	 */
	public void setBored(boolean bored) {
		this.bored = bored;
	}

	/**
	 * Drink.
	 * 
	 * @precondition none
	 * @postcondition isThirsty() == false
	 */
	public void drink() {
		this.thirsty = false;
	}

	/**
	 * Sleep.
	 * 
	 * @precondition none
	 * @postcondition isThirsty() == true && isBored() == true
	 */
	public void sleep() {
		this.thirsty = true;
		this.bored = true;
	}

	/**
	 * Play.
	 *
	 * @precondition none
	 * @postcondition isBored() == false && isHungry() == true && isThirsty() == true
	 */
	public void play() {
		this.bored = false;
		this.hungry = true;
		this.thirsty = true;
	}

	/**
	 * Spend night.
	 */
	public abstract void spendNight();

	/**
	 * Spend day.
	 */
	public abstract void spendDay();

	/**
	 * Gets the report for this player.
	 *
	 * @return the report
	 */
	public String getReport() {
		String report = "My name is " + this.getName() + System.lineSeparator();
		report += System.lineSeparator();
		if (!this.isThirsty() && !this.isHungry() && !this.isBored()) {
			report += "* I am content." + System.lineSeparator();
		} else {
			if (this.isThirsty()) {
				report += "* I am thirsty." + System.lineSeparator();
			}
			if (this.isHungry()) {
				report += "* I am hungry." + System.lineSeparator();
			}
			if (this.isBored()) {
				report += "* I am bored." + System.lineSeparator();
			}
		}
		return report;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Player: " + this.name + " (bet " + this.bet + ")";
	}

	@Override
	public int compareTo(Player player) {
		return this.getName().compareTo(player.getName());
	}
}
