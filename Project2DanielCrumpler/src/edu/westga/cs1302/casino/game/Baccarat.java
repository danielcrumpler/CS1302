package edu.westga.cs1302.casino.game;

import edu.westga.cs1302.casino.model.Card;
import edu.westga.cs1302.casino.model.Deck;
import edu.westga.cs1302.casino.model.Player;
import edu.westga.cs1302.casino.util.ExceptionMessages;

/**
 * The class Baccarat
 * 
 * @author Daniel Crumpler
 *
 */
public class Baccarat extends Game {

	/**
	 * Instantiates a new game of Baccarat.
	 * 
	 * @precondition none
	 * @postcondition a new game is created
	 */
	public Baccarat() {
		super();
	}

	/**
	 * Starts a new round of Baccarat.
	 * 
	 * @precondition none
	 * @postcondition ready to play
	 */
	@Override
	public void startNewRound() {
		super.setMessage("BACCARAT");
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
			if (currCard.getValue() == 10 || currCard.getValue() == 12 || currCard.getValue() == 13
					|| currCard.getValue() == 14) {
				score += 0;
			} else {
				score += currCard.getValue();
			}
		}
		return score % 10;
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
			if (currCard.getValue() == 10 || currCard.getValue() == 12 || currCard.getValue() == 13
					|| currCard.getValue() == 14) {
				score += 10;
			} else {
				score += currCard.getValue();
			}
		}
		return score % 10;
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
        this.dealerPlay();
        if (super.getHumanPlayer().getHand().size() >= 3 || this.getHumanHandScore() > 5) {
            super.setMessage("Must Stand.");
            if (super.getHumanPlayer().getMoney() <= 0) {
                super.setMessage("\nGame over.");
            }
        }
        return play;
	}
	
	private void dealerPlay() {
		if (this.getHumanHandScore() == 9 || this.getHumanHandScore() == 0) {
			if (this.getDealerHandScore() == 0 || this.getDealerHandScore() == 1 || this.getDealerHandScore() == 2
					|| this.getDealerHandScore() == 3) {
				super.getDealer().addCard(super.getDeck().draw());
			}
		} else if (this.getHumanHandScore() == 8) {
			if (this.getDealerHandScore() == 0 || this.getDealerHandScore() == 1 || this.getDealerHandScore() == 2) {
				super.getDealer().addCard(super.getDeck().draw());
			}
		} else if (this.getHumanHandScore() == 6 || this.getHumanHandScore() == 7) {
			if (!(this.getDealerHandScore() == 7)) {
				super.getDealer().addCard(super.getDeck().draw());
			}
		} else if (this.getHumanHandScore() == 4 || this.getHumanHandScore() == 5) {
			if (!(this.getDealerHandScore() == 6) || !(this.getDealerHandScore() == 7)) {
				super.getDealer().addCard(super.getDeck().draw());
			}
		} else if (this.getHumanHandScore() == 2 || this.getHumanHandScore() == 3) {
			if (this.getDealerHandScore() == 0 || this.getDealerHandScore() == 1 || this.getDealerHandScore() == 2
					|| this.getDealerHandScore() == 3 || this.getDealerHandScore() == 4) {
				super.getDealer().addCard(super.getDeck().draw());
			}
		}
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
		if (this.getHumanHandScore() > this.getDealerHandScore()) {
			super.setMessage("You win!");
			super.getHumanPlayer().setMoney(super.getHumanPlayer().getMoney() + this.getPot());
			super.addHumanWins();
		} else if (this.getHumanHandScore() < this.getDealerHandScore()) {
			super.setMessage("Dealer wins.");
			super.addDealerWins();
		} else {
			super.setMessage("Tie.");
			super.getHumanPlayer().setMoney(super.getHumanPlayer().getMoney() + (this.getPot() / 2));
			super.addTies();
		}
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
