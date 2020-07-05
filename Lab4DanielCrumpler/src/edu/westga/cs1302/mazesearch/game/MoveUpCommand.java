package edu.westga.cs1302.mazesearch.game;

/**
 * The Class MoveUpCommand.
 * 
 * @author CS1302
 */
public class MoveUpCommand extends Command {

	/**
	 * The command that moves up in the maze.
	 * 
	 * @precondition none
	 * @postcondition getMazeDate() == mazeData && getCommandType() ==
	 *                CommandType.MOVE_UP && getDescription() == "move up"
	 * @param mazeData the maze data
	 */
	public MoveUpCommand(MazeGameData mazeData) {
		super(mazeData, CommandType.MOVE_UP, "move up");
	}

	@Override
	public void execute() {
		this.getMazeGameData().moveUp();
	}
}
