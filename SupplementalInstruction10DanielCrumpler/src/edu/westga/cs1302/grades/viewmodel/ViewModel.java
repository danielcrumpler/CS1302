package edu.westga.cs1302.grades.viewmodel;

import edu.westga.cs1302.grades.model.CS2GradeCalculator;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * The class ViewModel.
 * 
 * @author CS 1302
 *
 */
public class ViewModel {

	private CS2GradeCalculator gradeCalc;

	private StringProperty errorProperty;
	private StringProperty gradeProperty;
	private StringProperty siProperty;

	private StringProperty[] labProperty;
	private StringProperty[] examProperty;
	private StringProperty[] projectProperty;

	public ViewModel() {

		this.gradeCalc = new CS2GradeCalculator();
		this.gradeProperty = new SimpleStringProperty();
		this.siProperty = new SimpleStringProperty();
		this.errorProperty = new SimpleStringProperty();

		this.labProperty = new StringProperty[CS2GradeCalculator.NUM_LABS];
		for (int i = 0; i < CS2GradeCalculator.NUM_LABS; i++) {
			this.labProperty[i] = new SimpleStringProperty();
		}
		this.examProperty = new StringProperty[CS2GradeCalculator.NUM_EXAMS];
		for (int i = 0; i < CS2GradeCalculator.NUM_EXAMS; i++) {
			this.examProperty[i] = new SimpleStringProperty();
		}
		this.projectProperty = new StringProperty[CS2GradeCalculator.NUM_PROJECTS];
		for (int i = 0; i < CS2GradeCalculator.NUM_PROJECTS; i++) {
			this.projectProperty[i] = new SimpleStringProperty();
		}
	}

	/**
	 * Grade property.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the grade property
	 */
	public StringProperty getGradeProperty() {
		return this.gradeProperty;
	}

	/**
	 * SI grade property.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the SI grade property
	 */
	public StringProperty getSiProperty() {
		return this.siProperty;
	}

	/**
	 * Error property.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the error property
	 */
	public StringProperty getErrorProperty() {
		return this.errorProperty;
	}

	/**
	 * Lab property.
	 * 
	 * @precondition index >= 0 && index < NUM_LABS
	 * @postcondition none
	 *
	 * @return the lab property
	 */
	public StringProperty getLabProperty(int index) {
		if (index < 0 || index >= CS2GradeCalculator.NUM_LABS) {
			throw new IllegalArgumentException("invalid lab index");
		}
		return this.labProperty[index];
	}

	/**
	 * Exam property.
	 * 
	 * @precondition index >= 0 && index < NUM_EXAMS
	 * @postcondition none
	 *
	 * @return the exam property
	 */
	public StringProperty getExamProperty(int index) {
		if (index < 0 || index >= CS2GradeCalculator.NUM_EXAMS) {
			throw new IllegalArgumentException("invalid exam index");
		}
		return this.examProperty[index];
	}

	/**
	 * Project property.
	 * 
	 * @precondition index >= 0 && index < NUM_PROJECTS
	 * @postcondition none
	 *
	 * @return the project property
	 */
	public StringProperty getProjectProperty(int index) {
		if (index < 0 || index >= CS2GradeCalculator.NUM_PROJECTS) {
			throw new IllegalArgumentException("invalid project index");
		}
		return this.projectProperty[index];
	}

	/**
	 * Updates the letter grade based on the user input.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the lab property
	 */
	public void updateGrades() {
		try {
			this.gradeCalc.setSiGrade(Double.parseDouble(this.getSiProperty().getValue()));
			for (int i = 0; i < CS2GradeCalculator.NUM_LABS; i++) {
				this.gradeCalc.setLab(i, Double.parseDouble(this.getLabProperty(i).getValue()));
			}
			for (int i = 0; i < CS2GradeCalculator.NUM_EXAMS; i++) {
				this.gradeCalc.setExam(i, Double.parseDouble(this.getExamProperty(i).getValue()));
			}
			for (int i = 0; i < CS2GradeCalculator.NUM_PROJECTS; i++) {
				this.gradeCalc.setProject(i, Double.parseDouble(this.getProjectProperty(i).getValue()));
			}
		} catch (Exception e) {
			this.errorProperty.set(e.getMessage());
		}

		this.gradeCalc.calculateLetterGrade();
		this.gradeProperty.set(this.gradeCalc.getLetterGrade());

	}
}
