package edu.westga.cs1302.mazesearch.game;

/**
 * The available values of CommandType.
 * 
 * @author CS1302
 */
public enum CommandType {
	MOVE_LEFT("H|h"), MOVE_RIGHT("K|k"), MOVE_UP("U|u"), MOVE_DOWN("J|j"), QUIT("Q|q");

	private String key;

	/**
	 * Creates a major with location.
	 * 
	 * @precondition !null && !key.isEmpty()
	 * @postcondition getKey() == key
	 * @param key the key to be assigned
	 */
	CommandType(String key) {
		this.key = key;
	}

	/**
	 * Returns this Commands key.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the key
	 */
	public String getKey() {
		return this.key;
	}
}
