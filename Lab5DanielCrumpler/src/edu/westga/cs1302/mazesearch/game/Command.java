package edu.westga.cs1302.mazesearch.game;

import edu.westga.cs1302.mazesearch.resources.ExceptionMessages;

/**
 * The Class Command.
 * 
 * @author CS1302
 */
public abstract class Command {

	private MazeGameData mazeData;
	private CommandType commandType;

	/**
	 * Instantiates a new move down command.
	 *
	 * @precondition mazeData != null && commandType != null
	 * @postcondition command operating on the specified maze game data
	 * 
	 * @param mazeData    the maze game data
	 * @param commandType the type of the command
	 */
	protected Command(MazeGameData mazeData, CommandType commandType) {
		if (mazeData == null) {
			throw new IllegalArgumentException(ExceptionMessages.NULL_MAZE_GAME_DATA);
		}
		if (commandType == null) {
			throw new IllegalArgumentException(ExceptionMessages.NULL_COMMAND_TYPE);
		}
		this.mazeData = mazeData;
		this.commandType = commandType;
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
		return this.commandType;
	}

	/**
	 * Executes this command.
	 *
	 * @precondition none
	 * @postcondition none
	 */
	public abstract void execute();

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
		if (commandKey != null && commandKey.matches(this.commandType.getKey())) {
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
	 * @return the key and description of the command type
	 */
	public String getUsage() {
		return this.commandType.getKey() + " - " + this.commandType.getDescription();
	}

}
