package edu.westga.cs1302.classroster.model;

import java.util.ArrayList;


/**
 * Defines a Roster.
 *
 * @author CS1302
 */
public class Roster {
	public static final String STUDENT_CANNOT_BE_NULL = "student cannot be null.";
	
	private ArrayList<Student> students;

	/**
	 * Makes a class roster.
	 *
	 * @precondition none
	 * @postcondition size() == size()@prev + 1
	 */
	public Roster() {
		this.students = new ArrayList<Student>();
	}

	/**
	 * Size of class roster.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return The size of the class roster
	 */
	public int size() {
		return this.students.size();
	}

	/**
	 * Adds the given student to the roster.
	 * 
	 * @precondition student != null
	 * @postcondition size() == size()@prev + 1
	 * 
	 * @param student
	 *            The student object to add to the roster
	 * 
	 * @return true if student successfully added, false otherwise
	 */
	public boolean add(Student student) {
		if (student == null) {
			throw new IllegalArgumentException(Roster.STUDENT_CANNOT_BE_NULL);
		}
		
		return this.students.add(student);
	}

	/**
	 * Counts all the students who have a grade between the specified lower and
	 * upper grades, inclusive.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param lowGrade
	 *            the low grade
	 * @param highGrade
	 *            the high grade
	 * 
	 * @return Count of number of grades between lowGrade and highGrade inclusive.
	 * 
	 */
	public int countGradesBetween(int lowGrade, int highGrade) {
		int total = 0;
		for (Student student : this.students) {
			if (student.getGrade() >= lowGrade && student.getGrade() <= highGrade) {
				total += 1;
			}
		}
		return total;
	}
	
	/**
	 * Counts the number of students in the class that contain the specified word.
	 * 
	 * @precondition value != null
	 * @postcondition none
	 * 
	 * @param value
	 *            The "word" (sequence of letters) to look for in the name
	 *            
	 * @return The count of students that contained the value "word" in their name
	 */
	public int countStudentsThatContain(String value) {
		if (value == null) {
			throw new IllegalArgumentException("value cannot be null");
		}
		int total = 0;
		for (Student student : this.students) {
			if (student.getName().toLowerCase().contains(value.toLowerCase())) {
				total += 1;
			}
		}
		return total;
	}
}
