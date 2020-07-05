package edu.westga.cs1302.mazesearch.game;

/**
 * The Class HelpCommand.
 * 
 * @author CS1302
 */
public class HelpCommand extends Command {

	/**
	 * Instantiates a new specialized command
	 *
	 * @precondition mazeData != null
	 * @postcondition getMazeDate() == mazeData && getCommandType() ==
	 *                CommandType.HELP
	 * @param mazeData the maze game data
	 */
	public HelpCommand(MazeGameData mazeData) {
		super(mazeData, CommandType.HELP);
	}

	/**
	 * Executes help command.
	 * 
	 * @precondition none
	 * @postcondition the state of the maze game is State.HELP
	 */
	@Override
	public void execute() {
		this.getMazeGameData().setState(State.HELP);
	}

	/**
	 * Help Usage.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the key and description of the command type and displays possible
	 *         moves
	 */
	@Override
	public String getUsage() {
		return super.getUsage() + " (displays possible moves)";
	}
}
