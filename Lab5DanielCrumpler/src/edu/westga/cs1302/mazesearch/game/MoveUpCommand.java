package edu.westga.cs1302.mazesearch.game;

/**
 * The Class MoveUpCommand.
 * 
 * @author CS1302
 */
public class MoveUpCommand extends Command {

	/**
	 * Instantiates a new specialized command
	 *
	 * @precondition mazeData != null
	 * @postcondition getMazeDate() == mazeData && getCommandType() ==
	 *                CommandType.MOVE_UP
	 * @param mazeData the maze game data
	 */
	public MoveUpCommand(MazeGameData mazeData) {
		super(mazeData, CommandType.MOVE_UP);
	}

	/**
	 * Executes moving up.
	 *
	 * @precondition none
	 * @postcondition current position has moved up one cell in the maze
	 */
	@Override
	public void execute() {
		this.getMazeGameData().moveUp();
	}
}
