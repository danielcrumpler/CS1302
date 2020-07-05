package edu.westga.cs1302.inheritance;

import edu.westga.cs1302.inheritance.controller.DemoController;

/**
 * Entry point for the program.
 * 
 * @author CS 1302
 */
public class Main {

	/**
	 * Default entry point for the program.
	 * 
	 * @param args command line arguments for the program
	 */
	public static void main(String[] args) {
		DemoController demo = new DemoController();
		demo.run();
	}
}
