package edu.westga.cs1302.mazesearch.view;

import java.util.Scanner;
import java.util.ArrayList;
import edu.westga.cs1302.mazesearch.game.MazeGameData;
import edu.westga.cs1302.mazesearch.resources.ExceptionMessages;
import edu.westga.cs1302.mazesearch.game.Command;

/**
 * The Class TUI.
 * 
 * @author CS1302
 */
public class TUI {

	private MazeGameData mazeData;
	private MazeViewBuilder mazeViewBuilder;
	private ArrayList<Command> commands;
	private Scanner console;

	/**
	 * Instantiates a new TUI.
	 * 
	 * @precondition maze != null && moveLeftCommand != null && moveRighttCommand !=
	 *               null && moveUpCommand != null && moveDownCommand != null &&
	 *               quitCommand != null
	 * @postcondition the TUI instance is a text-based user interface for the a maze
	 *                game represented by the passed in data
	 *
	 * @param maze     the maze
	 * @param commands the list of commands
	 */
	public TUI(MazeGameData maze, ArrayList<Command> commands) {
		if (maze == null) {
			throw new IllegalArgumentException(ExceptionMessages.NULL_MAZE_GAME_DATA);
		}
		if (commands == null) {
			throw new IllegalArgumentException(ExceptionMessages.NULL_COMMAND_LIST);
		}
		this.mazeData = maze;
		this.mazeViewBuilder = new MazeViewBuilder(this.mazeData.getMaze(), this.mazeData.getCurrentPosition());
		this.commands = commands;
		this.console = new Scanner(System.in);
	}

	/**
	 * Displays the maze.
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public void displayMaze() {
		this.mazeViewBuilder.updateCurrentPosition(this.mazeData.getCurrentPosition());
		String[] mazeDisplay = this.mazeViewBuilder.getMazeDisplay();
		String[] usageDisplay = this.createUsageDisplay();
		String spaces = "";
		for (int i = 0; i < mazeDisplay[0].length(); i++) {
			spaces += " ";
		}

		this.clearConsole();
		int maxLength = mazeDisplay.length > usageDisplay.length ? mazeDisplay.length : usageDisplay.length;
		for (int i = 0; i < maxLength; i++) {
			if (i < mazeDisplay.length) {
				System.out.print(mazeDisplay[i]);
			} else {
				System.out.print(spaces);
			}
			if (i < usageDisplay.length) {
				System.out.println("  " + usageDisplay[i]);
			} else {
				System.out.println();
			}
		}
	}

	/**
	 * Displays a message reflecting the state of the game
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public void displayGameState() {
		if (this.mazeData.getState().equals("quit")) {
			System.out.println("Good bye!");
		} else if (this.mazeData.getState().equals("goal found")) {
			this.mazeViewBuilder.updateCurrentPosition(this.mazeData.getCurrentPosition());
			String[] mazeDisplay = this.mazeViewBuilder.getMazeDisplay();
			this.clearConsole();
			for (int i = 0; i < mazeDisplay.length; i++) {
				System.out.println(mazeDisplay[i]);
			}
			System.out.println("Congratulation, you found the goal!");
		} else {
			System.out.println(this.mazeData.getState());
		}
	}

	/**
	 * Gets the user input.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the user input
	 */
	public String getUserInput() {
		String input;
		input = this.console.next();
		return input;
	}

	/**
	 * Create and array of strings explaining how to use each command.
	 * 
	 * @return the string[] of commands
	 */
	private String[] createUsageDisplay() {
		String[] usageDisplay = new String[6];
		usageDisplay[0] = "Commands:";
		int count = 1;
		for (Command currCommand : this.commands) {
			usageDisplay[count] = currCommand.getUsage();
			count += 1;
		}
		return usageDisplay;
	}

	/**
	 * Clear console.
	 */
	private void clearConsole() {
		for (int i = 0; i < 50; ++i) {
			System.out.println();
		}
	}
}
