package edu.westga.cs1302.lengthconverter.viewmodel;

import java.text.DecimalFormat;

import edu.westga.cs1302.lengthconverter.model.USLengthUnit;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * The Class ViewModel.
 * 
 * @author Daniel Crumpler
 */
public class ViewModel {

	private double length;
	private double scaleToMeter;
	private USLengthUnit unit;
	private DecimalFormat df = new DecimalFormat("#,##0.00");

	private StringProperty unitTextProperty;
	private StringProperty lengthTextProperty;
	private StringProperty outputTextProperty;
	private StringProperty errorMessageTextProperty;

	/**
	 * Instantiates a new ViewModel
	 */
	public ViewModel() {
		this.errorMessageTextProperty = new SimpleStringProperty();
		this.lengthTextProperty = new SimpleStringProperty();
		this.unitTextProperty = new SimpleStringProperty();
		this.outputTextProperty = new SimpleStringProperty();
	}

	/**
	 * Calculates the Length Conversion
	 */
	public void calculate() {
		try {
			this.length = Integer.parseInt(this.lengthTextProperty.get());
		} catch (Exception e) {
			this.errorMessageTextProperty.set(e.toString());
		}
		if (this.isAUnit()) {
			this.scaleToMeter = this.unit.scaleToMeter(this.length);
			this.outputTextProperty.set(this.outputString());
		} else {
			this.errorMessageTextProperty.set("invalid unit, use IN, YD, FT, or MI");
		}
	}

	private boolean isAUnit() {
		for (USLengthUnit currUnit : USLengthUnit.values()) {
			if (currUnit.name().equalsIgnoreCase(this.unitTextProperty.get())) {
				this.unit = currUnit;
				return true;
			}
		}
		return false;
	}

	private String outputString() {
		return this.df.format(this.scaleToMeter * 1000) + "mm" + System.lineSeparator()
				+ this.df.format(this.scaleToMeter * 100) + "cm" + System.lineSeparator()
				+ this.df.format(this.scaleToMeter) + "m " + System.lineSeparator()
				+ this.df.format(this.scaleToMeter * .001) + "km";
	}

	/**
	 * The error message text property.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the error message text property
	 */
	public StringProperty getErrorMessageTextProperty() {
		return this.errorMessageTextProperty;
	}

	/**
	 * The length text property.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the length text property
	 */
	public StringProperty getLengthTextProperty() {
		return this.lengthTextProperty;
	}

	/**
	 * The unit text property.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the unit text property
	 */
	public StringProperty getUnitTextProperty() {
		return this.unitTextProperty;
	}

	/**
	 * The output text property.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the output text property
	 */
	public StringProperty getOutputTextProperty() {
		return this.outputTextProperty;
	}

}
