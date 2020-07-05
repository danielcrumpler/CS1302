package edu.westga.cs1302.mazesearch.game;

/**
 * The Class MoveDownCommand.
 * 
 * @author CS1302
 */
public class MoveDownCommand extends Command {

	/**
	 * Instantiates a new specialized command to move down
	 *
	 * @precondition mazeData != null
	 * @postcondition getMazeDate() == mazeData && getCommandType() ==
	 *                CommandType.MOVE_DOWN && getDescription() == "move down"
	 * @param mazeData the maze game data
	 */
	public MoveDownCommand(MazeGameData mazeData) {
		super(mazeData, CommandType.MOVE_DOWN);
	}

	/**
	 * Executes moving down.
	 *
	 * @precondition none
	 * @postcondition current position has moved down one cell in the maze
	 */
	@Override
	public void execute() {
		this.getMazeGameData().moveDown();
	}

}
