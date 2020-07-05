
package edu.westga.cs1302.casino.model;

/**
 * The Class Dealer.
 * 
 * @author CS 1302
 */
public class Dealer extends Player {

	/**
	 * Instantiates a new player.
	 * 
	 * @precondition name != null && name not empty
	 * @postcondition getName() == name && getBet() == bet && isThirsty() == false
	 *                && isHungry() == false && isBored() == false
	 * 
	 * @param name the name
	 * @param bet  the bet
	 */
	public Dealer(String name, int bet) {
		super(name, bet);
	}

	@Override
	public void spendDay() {
		this.sleep();
	}

	@Override
	public void spendNight() {
		this.drink();
		this.play();
		this.setHungry(true);
	}

	@Override
	public String toString() {
		return "Dealer: " + this.getName() + " (bet " + this.getBet() + ")";
	}
}
