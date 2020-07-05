package edu.westga.cs1302.sandbox.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import edu.westga.cs1302.sandbox.model.LastNameComparator;
import edu.westga.cs1302.sandbox.model.Student;

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

		/**
		 * Lesson No. 1: Sorting arrays and array lists:
		 */
		System.out.println("Lesson 1: Sorting arrays and array lists:");
		String[] names = { "John", "Sarah", "Abby" };
		Arrays.sort(names);
		for (int i = 0; i < names.length; i++) {
			System.out.println(names[i]);
		}
		System.out.println();
		// Problem: java.lang.ClassCastException
		Student[] test = { new Student("Bugs", "Bunny", 97), new Student("Daffy", "Duck", 72) };
		Arrays.sort(test);

		/**
		 * Lesson No. 2: The "difference" trick -- why you have to be careful:
		 */
		System.out.println("Lesson 2: The difference trick -- why you have to be careful:");
		int number1 = 2147483647;
		int number2 = -2147483647;
		int difference = number1 - number2;
		System.out.println("This will not return 4294967294, but: " + difference);
		System.out.println();

		/**
		 * Lesson No. 3: Comparable interface compilation warnings
		 */
		System.out.println("Lesson 3: Comparable interface compilation warnings");
		ArrayList<Student> theRoster = new ArrayList<Student>();
		theRoster.add(new Student("Bugs", "Bunny", 97));
		theRoster.add(new Student("Daffy", "Duck", 72));
		theRoster.add(new Student("Yosemite", "Sam", 91));
		theRoster.add(new Student("Elmer", "Fudd", 82));
		theRoster.add(new Student("Road", "Runner", 85));
		theRoster.add(new Student("Honey", "Bunny", 100));
		
		Collections.sort(theRoster, new LastNameComparator()); //Comparator
		for (Student currStudent : theRoster) {
			System.out.println(currStudent);
		}
	}
}
