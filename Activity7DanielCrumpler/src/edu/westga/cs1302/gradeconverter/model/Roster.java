package edu.westga.cs1302.gradeconverter.model;

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
	 * 
	 * @precondition none
	 * @postcondition size() == 0
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
	 * @param student the student
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

	/**
	 * Counts students with specified major.
	 * 
	 * @param major the specified major
	 * @return number students with specified major
	 */
	public int countStudentsWithMajor(Major major) {
		int count = 0;
		for (Student student : this.students) {
			if (student.getMajor().equals(major)) {
				count++;
			}
		}
		return count;
	}
	
}
