package edu.westga.cs1302.casino.game;

import edu.westga.cs1302.casino.model.Card;
import edu.westga.cs1302.casino.model.Deck;
import edu.westga.cs1302.casino.model.Player;
import edu.westga.cs1302.casino.util.ExceptionMessages;

/**
 * The class Blackjack.
 * 
 * @author CS 1302
 */
public class Blackjack extends Game {

	/**
	 * Instantiates a new game of Blackjack.
	 * 
	 * @precondition none
	 * @postcondition a new game is created
	 */
	public Blackjack() {
		super();
	}

	/**
	 * Starts a new round of BlackJack.
	 * 
	 * @precondition none
	 * @postcondition ready to play
	 */
	@Override
	public void startNewRound() {
		super.setMessage("BLACKJACK");
		super.setDeck(new Deck());
		super.getDeck().shuffle();
		super.getDealer().emptyHand();
		super.getHumanPlayer().emptyHand();
	}

	/**
	 * Deals the initial hands.
	 * 
	 * @precondition deck.size() == 52
	 * @postcondition players get two cards each, deck.size() == deck.size()@prev -
	 *                4
	 */
	@Override
	public void dealHands() {
		super.getHumanPlayer().addCard(super.getDeck().draw());
		super.getDealer().addCard(super.getDeck().draw());
		super.getHumanPlayer().addCard(super.getDeck().draw());
		super.getDealer().addCard(super.getDeck().draw());
	}

	/**
	 * Calculates the score of the human hand, according to the BlackJack rules.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the score of the hand
	 */
	@Override
	public int getHumanHandScore() {
		int score = 0;
		for (Card currCard : super.getHumanPlayer().getHand()) {
			if (currCard.getValue() == 12 || currCard.getValue() == 13 || currCard.getValue() == 14) {
				score += 10;
			} else if (currCard.getValue() == 1) {
				score += 0;
			} else {
				score += currCard.getValue();
			}
		}
		for (Card currCard : super.getHumanPlayer().getHand()) {
			if (currCard.getValue() == 1) {
				score += 11;
				if (score > 21) {
					score -= 10;
				}
			}
		}
		return score;
	}

	/**
	 * Calculates the score of the dealer hand, according to the BlackJack rules.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the score of the hand
	 */
	@Override
	public int getDealerHandScore() {
		int score = 0;
		for (Card currCard : super.getDealer().getHand()) {
			if (currCard.getValue() == 12 || currCard.getValue() == 13 || currCard.getValue() == 14) {
				score += 10;
			} else if (currCard.getValue() == 1) {
				score += 0;
			} else {
				score += currCard.getValue();
			}
		}
		for (Card currCard : super.getDealer().getHand()) {
			if (currCard.getValue() == 1) {
				score += 11;
				if (score > 21) {
					score -= 10;
				}
			}
		}
		return score;
	}

	/**
	 * Hit: player asks for an additional card from the deck.
	 * 
	 * @precondition none
	 * @postcondition one card was dealt from the deck and added to the player's
	 *                hand; if the player busts, the dealer wins the round and the
	 *                message of the game is "Bust - you lose."
	 * @return false if player busted, true otherwise
	 */
	@Override
	public boolean hit() {
		boolean play = true;
		super.getHumanPlayer().addCard(super.getDeck().draw());
		if (this.getHumanHandScore() > 21) {
			super.setMessage("Bust - you lose.");
			play = false;
			if (super.getHumanPlayer().getMoney() <= 0) {
				super.setMessage("\nGame over.");
			}
		}
		return play;
	}

	/**
	 * Stand: player stands and now the dealer plays their turn.
	 * 
	 * @precondition the player finished their turn (they hit and not busted, and
	 *               now they stand in order to have the dealer turn play out
	 * @postcondition the game reached a resolution
	 */
	@Override
	public void stand() {
		if (this.getDealerHandScore() > 21) {
			super.setMessage("Dealer busts.");
			this.ifPlayerWins();
		} else if (this.getDealerHandScore() == 21 && this.getHumanHandScore() == 21
				&& super.getDealer().getHand().size() == 2 && super.getHumanPlayer().getHand().size() == 2
				&& this.getDealerHandScore() == this.getHumanHandScore()) {
			super.setMessage("Stand-off at Natural 21.");
			this.ifTie();
		} else if (this.getHumanHandScore() == 21 && super.getHumanPlayer().getHand().size() == 2) {
			super.setMessage("Natural 21 - you win 2 x pot!");
			super.getHumanPlayer().setMoney(super.getHumanPlayer().getMoney() + (this.getPot() * 2));
			super.addHumanWins();
		} else if (this.getDealerHandScore() == 21 && super.getDealer().getHand().size() == 2) {
			super.setMessage("Dealer wins with Natural 21.");
			super.addDealerWins();
		} else if (this.getDealerHandScore() == 21 && this.getHumanHandScore() == 21
				&& this.getDealerHandScore() == this.getHumanHandScore()) {
			super.setMessage("Stand-off.");
			this.ifTie();
		} else if (this.getHumanHandScore() == 21 && super.getHumanPlayer().getHand().size() > 2) {
			super.setMessage("You win!");
			this.ifPlayerWins();
		} else if (this.getDealerHandScore() == 21 && super.getDealer().getHand().size() > 2) {
			super.setMessage("Dealer wins.");
			super.addDealerWins();
		} else if (this.getHumanHandScore() > this.getDealerHandScore()) {
			super.setMessage("You win.");
			this.ifPlayerWins();
		} else if (this.getDealerHandScore() == this.getHumanHandScore()) {
			super.setMessage("Tie.");
			this.ifTie();
		}
	}

	private void ifTie() {
		super.getHumanPlayer().setMoney(super.getHumanPlayer().getMoney() + (this.getPot() / 2));
		super.addTies();
	}

	private void ifPlayerWins() {
		super.getHumanPlayer().setMoney(super.getHumanPlayer().getMoney() + this.getPot());
		this.addHumanWins();
	}

	/**
	 * Returns the money of the human player.
	 * 
	 * @precondition human player != null
	 * @postcondition none
	 * @param humanPlayer the specified human player
	 * @return the money of the human player
	 */
	public int getMoneyOf(Player humanPlayer) {
		if (humanPlayer == null) {
			throw new IllegalArgumentException(ExceptionMessages.NULL_PLAYER);
		}
		return this.getHumanPlayer().getMoney();
	}
}
