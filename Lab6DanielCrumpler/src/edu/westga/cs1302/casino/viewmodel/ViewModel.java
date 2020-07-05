package edu.westga.cs1302.casino.viewmodel;

import java.util.ArrayList;

import edu.westga.cs1302.casino.model.Game;
import edu.westga.cs1302.casino.model.Player;
import edu.westga.cs1302.casino.model.SortByAscendingBet;
import edu.westga.cs1302.casino.model.SortByDescendingBet;
import edu.westga.cs1302.casino.view.AlertProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * ViewModel mediates between the view and the rest of the program.
 * 
 * @author CS 1302
 *
 */
public class ViewModel {

	private StringProperty name;
	private IntegerProperty bet;
	private StringProperty playerType;
	private ObservableList<Player> playerList;
	private ObjectProperty<Player> selectedPlayer;
	private StringProperty playerInfo;
	private AlertProperty alertProperty;

	private Game game;

	/**
	 * Creates a new ViewModel object with its properties initialized.
	 * 
	 * @precondition alertProperty != null
	 * @postcondition none
	 * 
	 * @param alertProperty alert property to notify the GUI of alerts
	 */
	public ViewModel(AlertProperty alertProperty) {
		if (alertProperty == null) {
			throw new IllegalArgumentException("ViewModel requires non-null alert property.");
		}
		this.name = new SimpleStringProperty();
		this.bet = new SimpleIntegerProperty();
		this.playerType = new SimpleStringProperty();
		this.playerList = FXCollections.observableArrayList();
		this.selectedPlayer = new SimpleObjectProperty<Player>();
		this.playerInfo = new SimpleStringProperty();
		this.alertProperty = alertProperty;
		this.game = new Game("Players at BlackJack table");

		this.playerList.setAll(this.game.getHostedPlayers());
	}

	/**
	 * Returns name property.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the name property
	 */
	public StringProperty nameProperty() {
		return this.name;
	}

	/**
	 * Returns name property.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the name property
	 */
	public IntegerProperty ageProperty() {
		return this.bet;
	}

	/**
	 * Returns player type property.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the player type property
	 */
	public StringProperty playerTypeProperty() {
		return this.playerType;
	}

	/**
	 * Returns selected player property.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the player type property
	 */
	public ObjectProperty<Player> selectedPlayerProperty() {
		return this.selectedPlayer;
	}

	/**
	 * Returns player info property.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the player info property
	 */
	public StringProperty playerInfoProperty() {
		return this.playerInfo;
	}

	/**
	 * Returns list of players.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the name property
	 */
	public ObservableList<Player> getPlayers() {
		return this.playerList;
	}

	/**
	 * Returns a list of possible player types.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the types of possible players
	 */
	public ArrayList<String> getPlayerTypes() {
		return this.game.getPlayerTypes();
	}

	/**
	 * Adds a player that has been entered in the registration form.
	 * 
	 * @precondition none
	 * @postcondition game.getHostedPlayers().getNumberOfPlayers() ==
	 *                game.getHostedPlayers().getNumberOfPlayers()@prev + 1
	 */
	public void registerPlayer() {
		try {
			String name = this.name.getValue();
			int bet = this.bet.getValue();
			String playerType = this.playerType.getValue();

			this.game.addPlayer(playerType, name, bet);
			this.playerList.setAll(this.game.getHostedPlayers());
			this.alertProperty.set(AlertProperty.INFORMATION, "Registration successful",
					"Player " + name + " has been registered.");
		} catch (Exception e) {
			this.alertProperty.set(AlertProperty.ERROR, "Registration Error",
					"Entered data is invalid: " + e.getMessage());
		}
	}

	/**
	 * Updates a player using the data from the registration form.
	 * 
	 * @precondition none
	 * @postcondition game.getHostedPlayers() has been updated
	 */
	public void editPlayer() {
		String name = this.name.getValue();
		int bet = this.bet.getValue();
		String playerType = this.playerType.getValue().toLowerCase();

		Player player = this.selectedPlayer.getValue();

		try {
			String className = player.getClass().getName().toLowerCase();

			boolean updated = false;
			if (className.contains(playerType)) {
				player.setName(name);
				player.setBet(bet);
				updated = true;
			} else if (this.game.addPlayer(playerType, name, bet)) {
				this.game.removePlayer(player);
				updated = true;
			}

			if (updated) {
				this.playerList.setAll(this.game.getHostedPlayers());
				this.alertProperty.set(AlertProperty.INFORMATION, "Update successful",
						"The player " + name + " has been updated.");
			} else {
				this.alertProperty.set(AlertProperty.INFORMATION, "Update error",
						"Error countered updating player: " + name);
			}

		} catch (Exception e) {
			this.alertProperty.set(AlertProperty.ERROR, "Update error", e.getMessage());
		}
	}

	/**
	 * Removes the selected player.
	 * 
	 * @precondition none
	 * @postcondition game.getHostedPlayers().getNumberOfPlayers() ==
	 *                game.getHostedPlayers().getNumberOfPlayers())@prev - 1
	 */
	public void checkoutPlayer() {
		Player player = this.selectedPlayer.getValue();

		if (player != null) {
			this.alertProperty.set(AlertProperty.CONFIRMATION, "Checkout Confirmation",
					"Are you sure you want to check out " + player.getName() + "?");
			if (!this.alertProperty.getResult().equals("cancel")) {
				this.game.removePlayer(player);
				this.playerList.setAll(this.game.getHostedPlayers());

				this.alertProperty.set(AlertProperty.INFORMATION, "Checkout successful",
						"The player " + player.getName() + " has been checked out.");
			}
		} else {
			this.alertProperty.set(AlertProperty.INFORMATION, "Checkout failed", "No player has been selected");
		}
	}

	/**
	 * Sorts the casino's players.
	 *
	 * @precondition none
	 * @postcondition none
	 */
	public void sortPlayersByName() {
		this.game.sortPlayers();
		this.playerList.setAll(this.game.getHostedPlayers());
	}

	/**
	 * Sorts the game's players by bet amount in increasing order.
	 *
	 * @precondition none
	 * @postcondition none
	 */
	public void sortPlayersByBetInIncreasingOrder() {
		this.game.sortPlayers(new SortByAscendingBet());
		this.playerList.setAll(this.game.getHostedPlayers());
	}

	/**
	 * Sorts the casino's players by bet amount in decreasing order.
	 *
	 * @precondition none
	 * @postcondition none
	 */
	public void sortPlayersByBetInDecreasingOrder() {
		this.game.sortPlayers(new SortByDescendingBet());
		this.playerList.setAll(this.game.getHostedPlayers());
	}

	/**
	 * Serves food to the selected player.
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public void feedPlayer() {
		Player player = this.selectedPlayer.getValue();
		if (player == null) {
			this.alertProperty.set(AlertProperty.INFORMATION, "Serving food failed", "No player has been selected.");
		} else {
			player.setHungry(false);
		}
	}

	/**
	 * Serves drinks to the selected player.
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public void drinkToPlayer() {
		Player player = this.selectedPlayer.getValue();
		if (player == null) {
			this.alertProperty.set(AlertProperty.INFORMATION, "Serving drinks failed", "No player has been selected.");
		} else {
			player.drink();
		}
	}

	/**
	 * Upgrades the selected player.
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public void upgradePlayer() {
		Player player = this.selectedPlayer.getValue();
		if (player == null) {
			this.alertProperty.set(AlertProperty.INFORMATION, "Upgrade failed", "No player has been selected.");
		} else {
			player.play();
		}
	}

	/**
	 * Checks whether all players are content. If so, a night is simulated.
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public void closeForNight() {
		if (this.game.getThirstyPlayers().isEmpty() && this.game.getBoredPlayers().isEmpty()
				&& this.game.getHungryPlayers().isEmpty()) {
			this.game.simulateNight();
		} else {
			this.alertProperty.set(AlertProperty.INFORMATION, "Closing for the Night", "You cannot go home yet!",
					"Some players are not served.");
		}
	}

	/**
	 * Displays player info.
	 */
	public void displayPlayerInfo() {
		Player player = this.selectedPlayer.getValue();
		if (player != null) {
			String report = player.getReport();
			this.playerInfo.set(report);
		}
	}
}
