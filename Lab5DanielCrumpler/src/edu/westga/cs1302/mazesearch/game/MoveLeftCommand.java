package edu.westga.cs1302.mazesearch.game;

/**
 * The Class MoveLeftCommand.
 * 
 * @author CS1302
 */
public class MoveLeftCommand extends Command {

	/**
	 * Instantiates a new specialized command
	 *
	 * @precondition mazeData != null
	 * @postcondition getMazeDate() == mazeData && getCommandType() ==
	 *                CommandType.MOVE_LEFT
	 * @param mazeData the maze game data
	 */
	public MoveLeftCommand(MazeGameData mazeData) {
		super(mazeData, CommandType.MOVE_LEFT);
	}

	/**
	 * Executes moving left.
	 *
	 * @precondition none
	 * @postcondition current position has moved left one cell in the maze
	 */
	@Override
	public void execute() {
		this.getMazeGameData().moveLeft();
	}
}
