package edu.westga.cs1302.grades.model;

import edu.westga.cs1302.grades.resources.ExceptionMessages;

/**
 * Class Grade Calculator for CS2
 * 
 * @author CS 1302
 */
public class CS2GradeCalculator {
	public final static int NUM_LABS = 9;
	public final static int NUM_EXAMS = 3;
	public final static int NUM_PROJECTS = 3;

	private double[] labs = new double[NUM_LABS];
	private double[] exams = new double[NUM_EXAMS];
	private double[] projects = new double[NUM_PROJECTS];
	private double siGrade;

	private static final double LAB_WEIGHT = 0.01;
	private static final double EXAM_WEIGHT = 0.19;
	private static final double PROJECT1_WEIGHT = 0.09;
	private static final double PROJECT2_WEIGHT = 0.10;
	private static final double PROJECT3_WEIGHT = 0.10;

	private double total;
	private String letterGrade;

	/**
	 * Creates a new grade calculator.
	 * 
	 * @precondition none
	 * @postcondition necessary fields initialized
	 */
	public CS2GradeCalculator() {
		this.total = 0;
		this.letterGrade = "?";
	}

	/**
	 * Computes letter grade base on grades.
	 * 
	 * @precondition none
	 * @postcondition getLetterGrade() is the current letter grade
	 */
	public void calculateLetterGrade() {
		this.updateTotal();
		if (this.total >= 90) {
			this.letterGrade = this.total + "% => A";
		} else if (this.total >= 80) {
			this.letterGrade = this.total + "% => B";
		} else if (this.total >= 70) {
			this.letterGrade = this.total + "% => C";
		} else if (this.total >= 60) {
			this.letterGrade = this.total + "% => D";
		} else {
			this.letterGrade = this.total + "% => F";
		}
	}

	private void updateTotal() {
		this.total = 0;
		this.total += this.labsTotal(this.labs);
		this.total += this.projectsTotal(this.projects);
		this.total += this.examsTotal(this.exams);
		this.total += this.siGrade;
	}

	/**
	 * Gets the si grade.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the si grade
	 */
	public double getSiGrade() {
		return this.siGrade;
	}

	/**
	 * Sets the si to the specified grade.
	 * 
	 * @precondition siGrade
	 * @postcondition none
	 * @return the si grade
	 */
	public void setSiGrade(double siGrade) {
		if (siGrade < 0 || siGrade > 5) {
			throw new IllegalArgumentException(ExceptionMessages.INVALID_SI_GRADE);
		}
		this.siGrade = siGrade;
	}

	/**
	 * Gets the letter grade.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the letter grade
	 */
	public String getLetterGrade() {
		return this.letterGrade;
	}

	/**
	 * Sets the specified exam grade.
	 * 
	 * @param 0 < index < NUM_EXAMS && 0 <= grade <= 100
	 * @precondition getExam(index) == grade
	 * @postcondition none
	 * 
	 */
	public void setExam(int index, double grade) {
		if (index < 0 || index > NUM_EXAMS) {
			throw new IllegalArgumentException(ExceptionMessages.INVALID_EXAM_INDEX);
		}
		if (grade < 0 || grade > 100) {
			throw new IllegalArgumentException(ExceptionMessages.INVALID_EXAM_GRADE);
		}
		this.exams[index] = grade;
	}

	/**
	 * Gets the grade of the specified exam.
	 * 
	 * @param 0 < index < NUM_EXAMS
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the grade of the specified exam
	 */
	public double getExam(int index) {
		if (index < 0 || index > NUM_EXAMS) {
			throw new IllegalArgumentException(ExceptionMessages.INVALID_EXAM_INDEX);
		}
		return this.exams[index];
	}

	/**
	 * Sets the specified lab grade.
	 * 
	 * @param 0 < index < NUM_LABS && 0 <= grade <= 10
	 * @precondition getLab(index) == grade
	 * @postcondition none
	 * 
	 */
	public void setLab(int index, double grade) {
		if (index < 0 || index > NUM_LABS) {
			throw new IllegalArgumentException(ExceptionMessages.INVALID_LAB_INDEX);
		}
		if (grade != 0 && grade != 4 && grade != 7 && grade != 10) {
			throw new IllegalArgumentException(ExceptionMessages.INVALID_LAB_GRADE);
		}
		this.labs[index] = grade;
	}

	/**
	 * Gets the grade of the specified lab.
	 * 
	 * @param 0 < index < NUM_LABS
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the grade of the specified lab
	 */
	public double getLab(int index) {
		if (index < 0 || index > NUM_LABS) {
			throw new IllegalArgumentException(ExceptionMessages.INVALID_LAB_INDEX);
		}
		return this.labs[index];
	}

	/**
	 * Sets the specified project grade.
	 * 
	 * @param 0 < index < NUM_PROJECTS && 0 <= grade <= 100
	 * @precondition getProject(index) == grade
	 * @postcondition none
	 * 
	 */
	public void setProject(int index, double grade) {
		if (index < 0 || index > NUM_PROJECTS) {
			throw new IllegalArgumentException(ExceptionMessages.INVALID_PROJECT_INDEX);
		}
		if (grade < 0 || grade > 100) {
			throw new IllegalArgumentException(ExceptionMessages.INVALID_PROJECT_GRADE);
		}
		this.projects[index] = grade;
	}

	/**
	 * Gets the grade of the specified project.
	 * 
	 * @param 0 < index < NUM_PROJECTS
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the grade of the specified lab
	 */
	public double getProject(int index) {
		if (index < 0 || index > NUM_PROJECTS) {
			throw new IllegalArgumentException(ExceptionMessages.INVALID_PROJECT_INDEX);
		}
		return this.projects[index];
	}

	private double labsTotal(double[] labs) {
		double sum = 0;
		for (double grade : labs) {
			sum += grade * 10.0 * LAB_WEIGHT;
		}
		return sum;
	}

	private double projectsTotal(double[] projects) {
		double sum = 0;
		sum += this.projects[0] * PROJECT1_WEIGHT;
		sum += this.projects[1] * PROJECT2_WEIGHT;
		sum += this.projects[2] * PROJECT3_WEIGHT;
		return sum;
	}

	private double examsTotal(double[] exams) {
		double sum = 0;
		for (double grade : exams) {
			sum += grade * EXAM_WEIGHT;
		}
		return sum;
	}
}
