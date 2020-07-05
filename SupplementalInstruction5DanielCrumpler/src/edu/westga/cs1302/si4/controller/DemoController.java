package edu.westga.cs1302.si4.controller;

import java.util.Scanner;

/**
 * The Class DemoController.
 * 
 * @author CS1302
 */
public class DemoController {

	/**
	 * Demos functionality
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public void run() {
		
		this.workWithRegularExpressions();
	}

	private void workWithRegularExpressions() {

		try (Scanner input = new Scanner(System.in)) {
			String regex = this.promptUserForRegEx(input);
			boolean done = false;
				System.out.print("Enter a string: ");
				String line = input.nextLine();
				String lineUpperCase = line.toUpperCase();
				if (!lineUpperCase.equals("STOP") || !lineUpperCase.equals("QUIT") || !lineUpperCase.equals("BYE")) {
					done = false;
				} 
				if (done) {
					input.close();
				} else {
					boolean match = regex.contains(line);
					if (match) {
						System.out.println("Match");
					}
					else {
						System.out.println("NON match");
					}
				} 
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} 

	private String promptUserForRegEx(Scanner input) {
		String regex = "";
		System.out.print("Enter a regular expression: ");
		String line = input.nextLine();
		System.out.println("You have entered the regular expression: " + line);
		System.out.println();
		return regex;
	}
}
