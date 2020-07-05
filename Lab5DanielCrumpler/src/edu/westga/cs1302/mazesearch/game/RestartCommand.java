package edu.westga.cs1302.mazesearch.game;

/**
 * The Class RestartCommand.
 * 
 * @author CS1302
 */
public class RestartCommand extends Command {

	/**
	 * Instantiates a new specialized command
	 *
	 * @precondition mazeData != null
	 * @postcondition getMazeDate() == mazeData && getCommandType() ==
	 *                CommandType.RESTART
	 * @param mazeData the maze game data
	 */
	public RestartCommand(MazeGameData mazeData) {
		super(mazeData, CommandType.RESTART);
	}

	/**
	 * Executes restart command.
	 * 
	 * @precondition none
	 * @postcondition the state of the maze game is State.RESTART
	 */
	@Override
	public void execute() {
		this.getMazeGameData().setState(State.RESTART);
	}
}