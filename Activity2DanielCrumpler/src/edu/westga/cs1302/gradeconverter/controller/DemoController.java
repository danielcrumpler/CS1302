package edu.westga.cs1302.gradeconverter.controller;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import edu.westga.cs1302.gradeconverter.model.Student;

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
		Student sallie = new Student("Sallie Mae", 95);
		Student john = new Student("John Doe", 85);
		System.out.println(sallie);
		System.out.println(john);
		
		this.demoReadingInput();

		this.demoOpeningAFile();
	}

	/**
	 * Demos reading prompting and converting data for purposes of exception
	 * handling
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public void demoReadingInput() {
		boolean done = false;
		do {
			try {
				Scanner input = new Scanner(System.in);
				System.out.print("Enter a name: ");
				String name = input.nextLine();
				System.out.print("Enter a grade: ");
				String gradeInput = input.next();
				int grade = Integer.parseInt(gradeInput);
				Student aStudent = new Student(name, grade);
				System.out.println(aStudent);
				input.close();
			}	catch (Exception e) {
				System.err.print("Problem");
				System.out.println(e.getStackTrace());
			}
		} while (!done);
	}

	/**
	 * Demos creating file for purposes of dealing with checked exceptions,
	 * specifically an IOException.
	 *
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public void demoOpeningAFile() {
		 File outputFile = new File("test.txt");
		 try{
			 outputFile.createNewFile();
		 }catch(IOException e) {
			 e.printStackTrace();
		 }
	}

}
