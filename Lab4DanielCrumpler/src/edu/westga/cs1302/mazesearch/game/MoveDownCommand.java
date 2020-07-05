package edu.westga.cs1302.mazesearch.game;

/**
 * The Class MoveDownCommand.
 * 
 * @author CS1302
 */
public class MoveDownCommand extends Command {

	/**
	 * The command that moves down in the maze.
	 * 
	 * @precondition none
	 * @postcondition getMazeDate() == mazeData && getCommandType() ==
	 *                CommandType.MOVE_DOWN && getDescription() == "move down"
	 * @param mazeData the maze data
	 */
	public MoveDownCommand(MazeGameData mazeData) {
		super(mazeData, CommandType.MOVE_DOWN, "move down");
	}

	@Override
	public void execute() {
		this.getMazeGameData().moveDown();
	}
}
