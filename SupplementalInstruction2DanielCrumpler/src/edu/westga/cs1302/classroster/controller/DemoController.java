package edu.westga.cs1302.classroster.controller;

import edu.westga.cs1302.classroster.model.Roster;
import edu.westga.cs1302.classroster.model.Student;

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
		Student sallie = new Student("Sallie Mae", 96);
		Student john = new Student("John Doe", 85);
		System.out.println(sallie);
		System.out.println(john);
		
		Roster roster = new Roster();
		roster.add(sallie);
		roster.add(john);
		System.out.println("#students with A: " + roster.countGradesBetween(90, 100));
	}


}
