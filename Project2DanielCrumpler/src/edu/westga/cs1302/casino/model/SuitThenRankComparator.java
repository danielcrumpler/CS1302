package edu.westga.cs1302.casino.model;

import java.util.Comparator;

/**
 * The Comparator that compares Suit Then Rank
 * @author Crumpler
 *
 */
public class SuitThenRankComparator implements Comparator<Card> {

	@Override
	public int compare(Card card1, Card card2) {
		if (card1.getSuitValue() == card2.getSuitValue()) {
			return card1.compareTo(card2);
		} else {
			if (card1.getSuitValue() < card2.getSuitValue()) {
				return 1;
			} else if (card1.getSuitValue() > card2.getSuitValue()) {
				return -1;
			} else {
				return 0;
			}
		}
	}

}
