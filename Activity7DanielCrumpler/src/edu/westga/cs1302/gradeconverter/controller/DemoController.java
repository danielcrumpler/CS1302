package edu.westga.cs1302.gradeconverter.controller;

import edu.westga.cs1302.gradeconverter.model.Major;
import edu.westga.cs1302.gradeconverter.model.Roster;
import edu.westga.cs1302.gradeconverter.model.Student;

/**
 * The Class DemoController.
 * 
 * @author CS 1302
 */
public class DemoController {

	/**
	 * Demos functionality
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public void run() {
		Roster roster = this.populateRoster();

		System.out.println();
		this.printRoster(roster);

		System.out.println();
		System.out.print("Number computer science students: ");
		System.out.println(roster.countStudentsWithMajor(Major.COMPUTER_SCIENCE));

		System.out.println();
		System.out.print("Number mathematics students: ");
		System.out.println(roster.countStudentsWithMajor(Major.MATH));
	}

	private void printRoster(Roster roster) {
		System.out.println("Students in roster: ");
		for (Student currStudent : roster.getStudents()) {
			System.out.println(currStudent);
		}
	}

	private Roster populateRoster() {
		Roster students = new Roster();

		students.add(new Student("Bugs Bunny", Major.BIOLOGY));
		students.add(new Student("Yosemite Sam", Major.COMPUTER_SCIENCE));
		students.add(new Student("Elmer Fudd", Major.MATH));
		students.add(new Student("Road Runner", Major.CHEMISTRY));
		students.add(new Student("Honey Bunny", Major.COMPUTER_SCIENCE));
		students.add(new Student("Daffy Duck", Major.MATH));

		return students;
	}

}
