package edu.westga.cs1302.mazesearch.game;

/**
 * The Class MoveRightCommand.
 * 
 * @author CS1302
 */
public class MoveRightCommand extends Command {

	/**
	 * The command that moves right in the maze.
	 * 
	 * @precondition none
	 * @postcondition getMazeDate() == mazeData && getCommandType() ==
	 *                CommandType.MOVE_RIGHT && getDescription() == "move right"
	 * @param mazeData the maze data
	 */
	public MoveRightCommand(MazeGameData mazeData) {
		super(mazeData, CommandType.MOVE_RIGHT, "move right");
	}
	
	@Override
	public void execute() {
		this.getMazeGameData().moveRight();
	}

}
