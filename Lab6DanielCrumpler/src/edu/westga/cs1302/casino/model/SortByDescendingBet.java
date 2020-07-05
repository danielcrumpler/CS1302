package edu.westga.cs1302.casino.model;

import java.util.Comparator;

/**
 * The Class SortByDescendingBet.
 * 
 * @author CS 1302
 */
public class SortByDescendingBet implements Comparator<Player> {

	@Override
	public int compare(Player player1, Player player2) {
		if (player1.getBet() > player2.getBet()) {
			return -1;
		} else if (player1.getBet() < player2.getBet()) {
			return 1;
		} else {
			return 0;
		}
	}

}
