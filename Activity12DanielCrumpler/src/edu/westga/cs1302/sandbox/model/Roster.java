package edu.westga.cs1302.sandbox.model;

import java.util.ArrayList;

/**
 * The Class Roster.
 * 
 * @author CS 1302
 */
public class Roster {
	private ArrayList<Student> students;

	/**
	 * Instantiates a new roster.
	 */
	public Roster() {
		this.students = new ArrayList<Student>();
	}

	/**
	 * Adds specified student to class roster.
	 *
	 * @precondition student != null
	 * @postcondition size() == size()@prev + 1
	 * 
	 * @param student
	 *            the student
	 * @return true, if successful, false otherwise
	 */
	public boolean add(Student student) {
		if (student == null) {
			throw new IllegalArgumentException("student cannot be null.");
		}

		return this.students.add(student);
	}

	/**
	 * Number of students on class roster
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return Number of students on class roster
	 */
	public int size() {
		return this.students.size();
	}

	/**
	 * Gets the students.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the students
	 */
	public ArrayList<Student> getStudents() {
		return this.students;
	}
	

}
