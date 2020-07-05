package edu.westga.cs1302.si;

import edu.westga.cs1302.si.controller.DemoController;

/**
 * Entry point for the program.
 * 
 * @author CS1302
 */
public class Main {

	/**
	 * Default entry point for the program.
	 * 
	 * @param args
	 *            command line arguments for the program
	 */
	public static void main(String[] args) {
		DemoController demo = new DemoController();
		demo.run();
	}

}
