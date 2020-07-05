package edu.westga.cs1302.mazesearch;

import edu.westga.cs1302.mazesearch.controller.GameController;
import edu.westga.cs1302.mazesearch.datareader.MazeFileReader;

/**
 * The Class Main.
 * 
 * Entry point into the application
 * 
 * @author CS1302
 */
public class Main {

	/**
	 * The main method.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		GameController game = new GameController(new MazeFileReader("maze.txt"));
		game.start();
	}

}
