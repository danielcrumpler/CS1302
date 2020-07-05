package edu.westga.cs1302.casino.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import edu.westga.cs1302.casino.resources.UI;

/**
 * The Class PlayerCollection.
 * 
 * @author CS 1302
 */
public class PlayerCollection implements Sortable<Player>, Iterable<Player>, Collection<Player> {

	private ArrayList<Player> players;

	/**
	 * Instantiates a new player collection.
	 * 
	 * @precondition none
	 * @postcondition size() == 0
	 */
	public PlayerCollection() {
		this.players = new ArrayList<Player>();
	}

	/**
	 * Gets the size.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the size of this player collection
	 */
	public int size() {
		return this.players.size();
	}

	/**
	 * Adds the specified players to this collection.
	 * 
	 * @precondition player != null
	 * @postcondition size() = size()@prev + 1
	 * 
	 * @param player player to be added
	 * 
	 * @return true if the player was successfully added
	 */
	public boolean add(Player player) {
		if (player == null) {
			throw new NullPointerException(UI.PLAYER_CANNOT_BE_NULL);
		}
		return this.players.add(player);
	}

	/**
	 * Adds all players in the specified collection.
	 * 
	 * @precondition players != null && players does not contain a null element
	 * @postcondition size() = size()@prev + players.size()
	 * 
	 * @param players collection of players to be added
	 * 
	 * @return true if the players were successfully added
	 */
	public boolean addAll(Collection<? extends Player> players) {
		if (players == null) {
			throw new NullPointerException(UI.PLAYERS_CANNOT_BE_NULL);
		}
		if (players.contains(null)) {
			throw new NullPointerException(UI.PLAYERS_CANNOT_CONTAIN_NULL);
		}
		return this.players.addAll(players);
	}

	/**
	 * Removes the specified player from this collection.
	 * 
	 * @precondition players != null
	 * @postcondition size() = size()@prev - 1
	 * 
	 * @param player the player to be removed
	 * 
	 * @return true if the pet player removed successfully
	 */
	public boolean remove(Object player) {
		if (player == null) {
			throw new NullPointerException(UI.PLAYER_CANNOT_BE_NULL);
		}
		return this.players.remove(player);
	}

	/**
	 * Removes the players in the specified collection from this collection.
	 * 
	 * @precondition players != null && players does not contain a null element
	 * @postcondition none
	 * 
	 * @param players the players to be removed
	 * 
	 * @return true if the collection has changed
	 */
	public boolean removeAll(Collection<?> players) {
		if (players == null) {
			throw new NullPointerException(UI.PLAYERS_CANNOT_BE_NULL);
		}
		if (players.contains(null)) {
			throw new NullPointerException(UI.PLAYERS_CANNOT_CONTAIN_NULL);
		}
		return this.players.removeAll(players);
	}

	/**
	 * Removes the players not contained in the specified collection.
	 * 
	 * @precondition players != null && players does not contain a null element
	 * @postcondition none
	 * 
	 * @param players the players to be removed
	 * 
	 * @return true if the collection has changed
	 */
	public boolean retainAll(Collection<?> players) {
		if (players == null) {
			throw new NullPointerException(UI.PLAYERS_CANNOT_BE_NULL);
		}
		if (players.contains(null)) {
			throw new NullPointerException(UI.PLAYERS_CANNOT_CONTAIN_NULL);
		}
		return this.players.retainAll(players);
	}

	/**
	 * Returns an array representation of this collection.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return an array containing all of the elements in this collection
	 */
	public Object[] toArray() {
		return this.players.toArray();
	}

	/**
	 * Returns an array representation of this collection.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param <T>   the generic type
	 * @param array array to add pets of this collection to
	 * 
	 * @return an array containing all of the elements in this collection
	 */
	public <T> T[] toArray(T[] array) {
		return this.players.toArray(array);
	}

	@Override
	public void sort() {
		Collections.sort(this.players);
	}

	@Override
	public void sort(Comparator<Player> comparator) {
		Collections.sort(this.players, comparator);
	}

	@Override
	public Iterator<Player> iterator() {
		return this.players.iterator();
	}

	@Override
	public void clear() {
		this.players.clear();
	}

	@Override
	public boolean contains(Object arg0) {
		return this.players.contains(arg0);
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		return this.containsAll(arg0);
	}

	@Override
	public boolean isEmpty() {
		return this.players.isEmpty();
	}

}
