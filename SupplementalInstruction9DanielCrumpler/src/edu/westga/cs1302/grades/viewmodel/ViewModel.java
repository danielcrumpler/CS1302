package edu.westga.cs1302.grades.viewmodel;

import edu.westga.cs1302.model.CalculateGrades;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * The Class ViewModel.
 * 
 * @author Daniel Crumpler
 */
public class ViewModel {

	private CalculateGrades calculation;
	private StringProperty finalGradeTextProperty;
	private StringProperty exam1TextProperty;

	/**
	 * Instantiates a new ViewModel
	 */
	public ViewModel() {	
		this.exam1TextProperty = new SimpleStringProperty();
		this.finalGradeTextProperty = new SimpleStringProperty();
		this.calculation = new CalculateGrades();
	}
	
	public void calculate() {
		int grade = this.calculation.getFinalGrade(Integer.parseInt(this.exam1TextProperty.get()));
		this.finalGradeTextProperty.set("Final Grade: " + grade);
	}

	/**
	 * The final grade text property.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the final grade text property
	 */
	public StringProperty getFinalGradeTextProperty() {
		return this.finalGradeTextProperty;
	}

	/**
	 * The exam 1 text property.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the exam 1 text property
	 */
	public StringProperty getExam1TextProperty() {
		return this.exam1TextProperty;
	}

}
