package edu.westga.cs1302.mazesearch.game;

import edu.westga.cs1302.mazesearch.resources.ExceptionMessages;

/**
 * The Class Command.
 * 
 * @author CS1302
 */
public class Command {

	private MazeGameData mazeData;
	private CommandType type;
	private String description;

	/**
	 * Instantiates a new move down command.
	 *
	 * @precondition mazeData != null && commandType != null && description != null
	 *               && !description.isEmpty()
	 * @postcondition command operating on the specified maze game data
	 * 
	 * @param mazeData    the maze game data
	 * @param type        the type of the command
	 * @param description short command description
	 */
	public Command(MazeGameData mazeData, CommandType type, String description) {
		if (mazeData == null) {
			throw new IllegalArgumentException(ExceptionMessages.NULL_MAZE_GAME_DATA);
		}
		if (type == null) {
			throw new IllegalArgumentException(ExceptionMessages.NULL_COMMAND_TYPE);
		}
		if (description == null) {
			throw new IllegalArgumentException(ExceptionMessages.NULL_DESCRIPTION);
		}
		this.mazeData = mazeData;
		this.type = type;
		this.description = description;
	}

	/**
	 * Gets the maze game data.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the maze game data
	 */
	public MazeGameData getMazeGameData() {
		return this.mazeData;
	}

	/**
	 * Gets the command type.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the command type
	 */
	public CommandType getCommandType() {
		return this.type;
	}

	/**
	 * Executes this command.
	 *
	 * @precondition none
	 * @postcondition none
	 */
	public void execute() {
		return;
	}

	/**
	 * Checks if this command is evoked by the specified string.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param commandKey the command key to be checked
	 * @return true, if this command is evoked by the specified key
	 */
	public boolean isEvokedBy(String commandKey) {
		if (commandKey != null && commandKey.matches(this.type.getKey())) {
			return true;
		}
		return false;
	}

	/**
	 * Usage.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the key and description of the command, in the format: x|X -
	 *         <description of command X>
	 */
	public String getUsage() {
		return this.type.getKey() + " - " + this.description;
	}
}
