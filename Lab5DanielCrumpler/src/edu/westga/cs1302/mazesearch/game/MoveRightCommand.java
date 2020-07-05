package edu.westga.cs1302.mazesearch.game;

/**
 * The Class MoveRightCommand.
 * 
 * @author CS1302
 */
public class MoveRightCommand extends Command {

	/**
	 * Instantiates a new specialized command
	 *
	 * @precondition mazeData != null
	 * @postcondition getMazeDate() == mazeData && getCommandType() ==
	 *                CommandType.MOVE_RIGHT
	 * @param mazeData the maze game data
	 */
	public MoveRightCommand(MazeGameData mazeData) {
		super(mazeData, CommandType.MOVE_RIGHT);
	}

	/**
	 * Executes moving right.
	 *
	 * @precondition none
	 * @postcondition current position has moved right one cell in the maze
	 */
	@Override
	public void execute() {
		this.getMazeGameData().moveRight();
	}
}
