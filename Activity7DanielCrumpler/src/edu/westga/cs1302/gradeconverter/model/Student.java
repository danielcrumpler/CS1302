package edu.westga.cs1302.gradeconverter.model;

/**
 * Defines a student
 * 
 * @author CS 1302
 */
public class Student {
	private String name;
	private Major major;

	/**
	 * Creates a new Student with the specified name and grade.
	 * 
	 * @precondition name!=null AND !name.isEmpty() AND major != null AND
	 *               !maojr.isEmpty()
	 * @postcondition getName() == name AND getMajor() == major
	 * 
	 * @param name  name of the student
	 * @param major the students major
	 */
	public Student(String name, Major major) {
		if (name == null) {
			throw new IllegalArgumentException("name cannot be null.");
		}

		if (name.isEmpty()) {
			throw new IllegalArgumentException("name cannot be empty.");
		}

		if (major == null) {
			throw new IllegalArgumentException("major cannot be null.");
		}

		this.name = name;
		this.major = major;
	}

	/**
	 * Returns the Student's major
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the Student's grade
	 */
	public Major getMajor() {
		return this.major;
	}

	/**
	 * Returns the Student's name
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the Student's name
	 */
	public String getName() {
		return this.name;
	}

	@Override
	public String toString() {
		return this.name + ": " + this.major;
	}

}
