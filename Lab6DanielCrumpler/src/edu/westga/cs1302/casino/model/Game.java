package edu.westga.cs1302.casino.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

import edu.westga.cs1302.casino.resources.UI;

/**
 * The class Game.
 * 
 * @author CS 1302
 */
public class Game {

	private String name;
	private PlayerCollection hostedPlayers;
	private ArrayList<String> playerTypes;

	/**
	 * Instantiates a new game.
	 *
	 * @precondition name != null && name not empty
	 * @postcondition getName() == name
	 * 
	 * @param name the name of the game
	 */
	public Game(String name) {
		if (name == null) {
			throw new IllegalArgumentException(UI.NAME_CANNOT_BE_NULL);
		}

		if (name.isEmpty()) {
			throw new IllegalArgumentException(UI.NAME_CANNOT_BE_EMPTY);
		}

		this.name = name;
		this.hostedPlayers = new PlayerCollection();

		this.playerTypes = new ArrayList<String>();
		this.playerTypes.add("Dealer");
		this.playerTypes.add("Premium Player");
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
	 * Returns the number of hosted players.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the number of hosted players.
	 */
	public int getNumberOfPlayers() {
		return this.hostedPlayers.size();
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
	 * Gets the hosted players.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the hosted players
	 */
	public Collection<Player> getHostedPlayers() {
		return this.hostedPlayers;
	}

	/**
	 * Gets list of types of hosted players.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the player types
	 */
	public ArrayList<String> getPlayerTypes() {
		return this.playerTypes;
	}

	/**
	 * Adds the player to the list of hosted players.
	 *
	 * @precondition player != null
	 * @postcondition getNumberOfPlayers() == getNumberOfPlayers()@prev + 1
	 * 
	 * @param player The player to add
	 * 
	 * @return true if the player was added successfully to the list of hosted
	 *         players, false otherwise
	 */
	public boolean addPlayer(Player player) {
		if (player == null) {
			throw new IllegalArgumentException(UI.PLAYER_CANNOT_BE_NULL);
		}

		return this.hostedPlayers.add(player);
	}

	/**
	 * Adds the player to the list of hosted player.
	 *
	 * @precondition type != null && (type equals "Dealer" or "ComputerPlayer" or
	 *               "HumanPlayer") && name != null && name is not empty && bet > 0
	 *
	 * @postcondition getNumberOfPlayers() == getNumberOfPlayers()@prev + 1
	 * 
	 * @param type the name of the player type
	 * @param name the name of the player
	 * @param bet  the bet of the player
	 * 
	 * @return true, if the player was added successfully to the list of hosted
	 *         players
	 */
	public boolean addPlayer(String type, String name, int bet) {
		if (type == null) {
			throw new IllegalArgumentException(UI.TYPE_CANNOT_BE_NULL);
		}
		if (!type.equalsIgnoreCase("Dealer") && !type.equalsIgnoreCase("Premium Player")) {
			throw new IllegalArgumentException(UI.TYPE_INVALID);
		}
		if (name == null) {
			throw new IllegalArgumentException(UI.NAME_CANNOT_BE_NULL);
		}
		if (name.isEmpty()) {
			throw new IllegalArgumentException(UI.NAME_CANNOT_BE_EMPTY);
		}
		if (bet <= 0) {
			throw new IllegalArgumentException(UI.BET_MUST_BE_POSITIVE);
		}

		Player player = null;
		if (type.equalsIgnoreCase("Dealer")) {
			player = new Dealer(name, bet);
		} else {
			player = new PremiumPlayer(name, bet);
		}
		return this.hostedPlayers.add(player);
	}

	/**
	 * Removes the specified player from the list of hosted players.
	 *
	 * @precondition player != null
	 * @postcondition if found getNumberOfPlayers() == getNumberOfPlayers()@prev - 1
	 * 
	 * @param player player to delete
	 * 
	 * @return true if the player was successfully deleted from the list of hosted
	 *         players
	 */
	public boolean removePlayer(Player player) {
		if (player == null) {
			throw new IllegalArgumentException(UI.PLAYER_CANNOT_BE_NULL);
		}

		return this.hostedPlayers.remove(player);
	}

	/**
	 * Gets the report.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the report
	 */
	public String getReport() {
		String report = "";
		for (Player player : this.hostedPlayers) {
			report += player.getReport() + System.lineSeparator();
		}
		return report;
	}

	/**
	 * Simulate a day.
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public void simulateDay() {
		for (Player player : this.hostedPlayers) {
			player.spendDay();
		}
	}

	/**
	 * Simulate a night.
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public void simulateNight() {
		for (Player player : this.hostedPlayers) {
			player.spendNight();
		}
	}

	/**
	 * Gets list of thirsty players.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return list of hungry players
	 */
	public ArrayList<Player> getThirstyPlayers() {
		ArrayList<Player> thirstyPlayers = new ArrayList<Player>();
		for (Player player : this.hostedPlayers) {
			if (player.isThirsty()) {
				thirstyPlayers.add(player);
			}
		}
		return thirstyPlayers;
	}

	/**
	 * Gets list of bored players.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return list of bored players
	 */
	public ArrayList<Player> getBoredPlayers() {
		ArrayList<Player> boredPlayers = new ArrayList<Player>();
		for (Player player : this.hostedPlayers) {
			if (player.isBored()) {
				boredPlayers.add(player);

			}
		}
		return boredPlayers;
	}

	/**
	 * Gets list of hungry players.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return list of hungry players
	 */
	public ArrayList<Player> getHungryPlayers() {
		ArrayList<Player> hungryPlayers = new ArrayList<Player>();
		for (Player player : this.hostedPlayers) {
			if (player.isHungry()) {
				hungryPlayers.add(player);
			}
		}
		return hungryPlayers;
	}

	/**
	 * The method sorts the hosted players according to the chosen comparator
	 * ordering defined on the Player class.
	 * 
	 * @precondition none
	 * @postcondition players in natural order
	 * 
	 */
	public void sortPlayers() {
		this.hostedPlayers.sort();
	}

	/**
	 * The method sorts the hosted players according to the natural order defined on
	 * the Player class.
	 * 
	 * @precondition none
	 * @postcondition players in comparators defined order
	 * 
	 * @param comparator a comparator for class player
	 */
	public void sortPlayers(Comparator<Player> comparator) {
		this.hostedPlayers.sort(comparator);
	}
}
