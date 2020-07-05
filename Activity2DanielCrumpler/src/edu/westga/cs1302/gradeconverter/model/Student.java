package edu.westga.cs1302.gradeconverter.model;

/**
 * Defines a student
 * 
 * @author CS1302
 */
public class Student {
	private static final String GRADE_OUT_OF_RANGE = "grade must be >= 0 and <= 100";
	private String name;
	private int grade;

	/**
	 * Creates a new Student with the specified name and grade.
	 * 
	 * @precondition name!=null && !name.isEmpty() && 
	 * 				 grade >=0 && grade <= 100
	 * @postcondition getName() == name && getGrade() == grade
	 * 
	 * @param name
	 *            name of the student
	 * @param grade
	 *            the students numeric grade (between 0 and 100, inclusive)
	 */
	public Student(String name, int grade) {
		if (name == null) {
			throw new IllegalArgumentException("name cannot be null.");
		}
		
		if (name.isEmpty()) {
			throw new IllegalArgumentException("name cannot be empty.");
		}
		
		if (grade < 0 || grade > 100) {
			throw new IllegalArgumentException(Student.GRADE_OUT_OF_RANGE);
		}
		
		this.name = name;
		this.grade = grade;
	}

	/**
	 * Returns the Student's grade
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the Student's grade
	 */
	public int getGrade() {
		return this.grade;
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
		return this.name + ": " + this.grade;
	}
	
	
}
