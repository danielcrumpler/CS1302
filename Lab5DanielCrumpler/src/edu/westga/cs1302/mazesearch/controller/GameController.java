package edu.westga.cs1302.mazesearch.controller;

import java.util.ArrayList;

import edu.westga.cs1302.mazesearch.datareader.MazeFileReader;
import edu.westga.cs1302.mazesearch.game.Command;
import edu.westga.cs1302.mazesearch.game.HelpCommand;
import edu.westga.cs1302.mazesearch.game.MazeGameData;
import edu.westga.cs1302.mazesearch.game.MoveDownCommand;
import edu.westga.cs1302.mazesearch.game.MoveLeftCommand;
import edu.westga.cs1302.mazesearch.game.MoveRightCommand;
import edu.westga.cs1302.mazesearch.game.MoveUpCommand;
import edu.westga.cs1302.mazesearch.game.QuitCommand;
import edu.westga.cs1302.mazesearch.game.RestartCommand;
import edu.westga.cs1302.mazesearch.game.State;
import edu.westga.cs1302.mazesearch.resources.ExceptionMessages;
import edu.westga.cs1302.mazesearch.view.TUI;

/**
 * The Class GameController.
 * 
 * @author CS1302
 */
public class GameController {

	private MazeGameData mazeData;
	private TUI tui;
	private ArrayList<Command> commands;

	/**
	 * Instantiates a new game controller.
	 * 
	 * @precondition mazeReader != null
	 * @postcondition all fields are initialized
	 * 
	 * @param mazeReader maze reader
	 */
	public GameController(MazeFileReader mazeReader) {
		if (mazeReader == null) {
			throw new IllegalArgumentException(ExceptionMessages.MAZE_READER);
		}

		this.mazeData = new MazeGameData(mazeReader.getMaze());

		this.commands = new ArrayList<Command>();
		this.commands.add(new MoveLeftCommand(this.mazeData));
		this.commands.add(new MoveRightCommand(this.mazeData));
		this.commands.add(new MoveUpCommand(this.mazeData));
		this.commands.add(new MoveDownCommand(this.mazeData));
		this.commands.add(new QuitCommand(this.mazeData));
		this.commands.add(new RestartCommand(this.mazeData));
		this.commands.add(new HelpCommand(this.mazeData));

		this.tui = new TUI(this.mazeData, this.commands);
	}

	/**
	 * Start the maze game.
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public void start() {

		while (this.mazeData.play()) {
			if (this.mazeData.getState() == State.QUIT && this.tui.getQuitConfirmation(State.QUIT)) {
				break;
			}

			if (this.mazeData.getState() == State.HELP) {
				this.tui.displayMaze(this.mazeData.getPossibleMoves());
			} else {
				this.tui.displayMaze();
			}

			String input = this.tui.getUserInput();
			for (Command command : this.commands) {
				if (command.isEvokedBy(input)) {
					command.execute();
				}
			}
		}
		this.tui.displayGameState();
	}
}
