package edu.westga.cs1302.sandbox.model;

/**
 * Defines a student
 * 
 * @author CS 1302
 */
public class Student implements Comparable<Student> {
	private static final String GRADE_OUT_OF_RANGE = "Grade out of range of 0 to 100, inclusive.";
	private static final String GPA_OUT_OF_RANGE = "GPA out of range of 0.0 to 4.0, inclusive.";
	private String firstName;
	private String lastName;
	private int grade;
	private int height;
	private double gpa;

	/**
	 * Creates a new Student with the specified name and grade.
	 *
	 * @precondition firstName!=null AND !firstName.isEmpty() AND lastName!=null AND
	 *               !lastName.isEmpty() AND grade >=0 AND grade <= 100
	 * @postcondition getFirstName() == firstName AND getLastName() == lastName AND
	 *                getGrade() == grade
	 * 
	 * @param firstName
	 *            the first name
	 * @param lastName
	 *            the last name
	 * @param grade
	 *            the student's numeric grade (between 0 and 100, inclusive)
	 * 
	 */
	public Student(String firstName, String lastName, int grade) {
		if (firstName == null) {
			throw new IllegalArgumentException("firstName cannot be null.");
		}

		if (firstName.isEmpty()) {
			throw new IllegalArgumentException("firstName cannot be empty.");
		}
		
		if (lastName == null) {
			throw new IllegalArgumentException("lastName cannot be null.");
		}

		if (lastName.isEmpty()) {
			throw new IllegalArgumentException("lastName cannot be empty.");
		}

		if (grade < 0 || grade > 100) {
			throw new IllegalArgumentException(GRADE_OUT_OF_RANGE);
		}

		this.firstName = firstName;
		this.lastName = lastName;
		this.grade = grade;
	}
	
	/**
	 * Creates a new Student with the specified name and grade.
	 *
	 * @precondition firstName!=null AND !firstName.isEmpty() AND lastName!=null AND
	 *               !lastName.isEmpty() AND grade >=0 AND grade <= 100
	 * @postcondition getFirstName() == firstName AND getLastName() == lastName AND
	 *                getGrade() == grade
	 * 
	 * @param firstName
	 *            the first name
	 * @param lastName
	 *            the last name
	 * @param grade
	 *            the student's numeric grade (between 0 and 100, inclusive)
	 * 
	 */
	public Student(String firstName, String lastName, int grade, int height, double gpa) {
		if (firstName == null) {
			throw new IllegalArgumentException("firstName cannot be null.");
		}

		if (firstName.isEmpty()) {
			throw new IllegalArgumentException("firstName cannot be empty.");
		}
		
		if (lastName == null) {
			throw new IllegalArgumentException("lastName cannot be null.");
		}

		if (lastName.isEmpty()) {
			throw new IllegalArgumentException("lastName cannot be empty.");
		}

		if (grade < 0 || grade > 100) {
			throw new IllegalArgumentException(GRADE_OUT_OF_RANGE);
		}
		
		if (gpa < 0.0 || gpa > 4.0) {
			throw new IllegalArgumentException(GPA_OUT_OF_RANGE);
		}
		if (height < 0) {
			throw new IllegalArgumentException("Height must be greater than zero in inches");
		}

		this.firstName = firstName;
		this.lastName = lastName;
		this.grade = grade;
		this.height = height;
		this.gpa = gpa;
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
	 * Returns the Student's first name
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the Student's first name
	 */
	public String getFirstName() {
		return this.firstName;
	}
	
	/**
	 * Returns the Student's last name
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the Student's last name
	 */
	public String getLastName() {
		return this.lastName;
	}
	
	/**
	 * Returns the Student's height
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the Student's height
	 */
	public int getHeight() {
		return this.height;
	}
	
	/**
	 * Returns the Student's GPA
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the Student's GPA
	 */
	public double getGPA() {
		return this.gpa;
	}
	
	@Override
	public int compareTo(Student otherStudent) {
		if (this.grade > otherStudent.getGrade()) {
			return 1;
		} else if (this.grade < otherStudent.getGrade()) {
			return -1;
		} else {
			return 0;
		}
	}
	
	@Override
	public String toString() {
		return this.firstName + " " + this.lastName + ": " + this.grade;
	}
}
