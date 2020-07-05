package edu.westga.cs1302.casino.model;

/**
 * The class PremiumPlayer.
 * 
 * @author CS 1302
 */
public class PremiumPlayer extends Player {

	/**
	 * Instantiates a new premium player.
	 * 
	 * @precondition name != null && name not empty
	 * @postcondition getName() == name && getBet() == bet && isThirsty() == true
	 *                && isHungry() == false && isBored() == false
	 * 
	 * @param name the name
	 * @param bet  the bet
	 */
	public PremiumPlayer(String name, int bet) {
		super(name, bet);
		this.setThirsty(true);
	}

	@Override
	public void spendDay() {
		this.sleep();
	}

	@Override
	public void spendNight() {
		this.drink();
		this.play();
	}

	@Override
	public String toString() {
		return "Premium Player: " + this.getName() + " (bet " + this.getBet() + ")";
	}
}
