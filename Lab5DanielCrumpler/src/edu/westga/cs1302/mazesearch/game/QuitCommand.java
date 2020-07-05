package edu.westga.cs1302.mazesearch.game;

/**
 * The Class QuitCommand.
 * 
 * @author CS1302
 */
public class QuitCommand extends Command {

	/**
	 * Instantiates a new specialized command
	 *
	 * @precondition mazeData != null
	 * @postcondition getMazeDate() == mazeData && getCommandType() ==
	 *                CommandType.QUIT
	 * @param mazeData the maze game data
	 */
	public QuitCommand(MazeGameData mazeData) {
		super(mazeData, CommandType.QUIT);
	}

	/**
	 * Executes quit command.
	 * 
	 * @precondition none
	 * @postcondition the state of the maze game is State.QUIT
	 */
	@Override
	public void execute() {
		this.getMazeGameData().setState(State.QUIT);
	}

	/**
	 * Quit Usage.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the key and description of the command type and says requires
	 *         confirmation
	 */
	@Override
	public String getUsage() {
		return super.getUsage() + " (requires confirmation)";
	}
}
