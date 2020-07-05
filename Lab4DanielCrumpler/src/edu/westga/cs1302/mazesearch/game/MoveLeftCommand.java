package edu.westga.cs1302.mazesearch.game;

/**
 * The Class MoveDownCommand.
 * 
 * @author CS1302
 */
public class MoveLeftCommand extends Command {

	/**
	 * The command that moves left in the maze.
	 * 
	 * @precondition none
	 * @postcondition getMazeDate() == mazeData && getCommandType() ==
	 *                CommandType.MOVE_LEFT && getDescription() == "move left"
	 * @param mazeData the maze data
	 */
	public MoveLeftCommand(MazeGameData mazeData) {
		super(mazeData, CommandType.MOVE_LEFT, "move left");
	}

	@Override
	public void execute() {
		this.getMazeGameData().moveLeft();
	}

}
