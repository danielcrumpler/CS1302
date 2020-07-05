package edu.westga.cs1302.mazesearch.game;

/**
 * The Enum CommandType that defined a command along with its key(s).
 * 
 * @author CS1302
 */
public enum CommandType {

	MOVE_LEFT("H|h", "move left"), MOVE_RIGHT("K|k", "move right"), MOVE_UP("U|u", "move up"),
	MOVE_DOWN("J|j", "move down"), QUIT("Q|q", "quit"), RESTART("R|r", "restart"), HELP("E|e", "help");

	private final String key;
	private final String description;

	/**
	 * Creates a type of command with the specified key.
	 * 
	 * @precondition key != null && !key.isEmpty() && description != null &
	 *               !descrption.isEmpty()
	 * @postcondition command type was created with the given key
	 * @param key         the key of this command
	 * @param description the description of this command
	 */
	CommandType(String key, String description) {
		this.key = key;
		this.description = description;
	}

	/**
	 * Returns the key associated with this move.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the key to perform this command type
	 */
	public String getKey() {
		return this.key;
	}

	/**
	 * Returns the description associated with this command type.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the description of this command type
	 */
	public String getDescription() {
		return this.description;
	}
}
