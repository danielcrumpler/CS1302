package edu.westga.cs1302.mazesearch.game;

/**
 * The Class QuitCommand.
 * 
 * @author CS1302
 */
public class QuitCommand extends Command {

	/**
	 * The command that quits the maze.
	 * 
	 * @precondition none
	 * @postcondition getMazeDate() == mazeData && getCommandType() ==
	 *                CommandType.QUIT && getDescription() == "quit"
	 * @param mazeData the maze data
	 */
	public QuitCommand(MazeGameData mazeData) {
		super(mazeData, CommandType.QUIT, "quit");

	}
	
	@Override
	public void execute() {
		this.getMazeGameData().setState("quit");
	}
}
