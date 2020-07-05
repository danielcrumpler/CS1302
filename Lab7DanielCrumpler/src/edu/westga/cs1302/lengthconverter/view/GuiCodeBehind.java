package edu.westga.cs1302.lengthconverter.view;

import edu.westga.cs1302.lengthconverter.viewmodel.ViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * The class GuiCodeBehind
 * 
 * @author Daniel Crumpler
 *
 */
public class GuiCodeBehind {

	private ViewModel theViewModel;

	@FXML
	private Label errorMessageLabel;

	@FXML
	private TextField lengthTextField;

	@FXML
	private TextField unitTextField;

	@FXML
	private TextArea outputTextArea;

	@FXML
	void handleTextButton(ActionEvent event) {
		this.theViewModel.calculate();
	}

	/**
	 * Instantiates a new GuiCodeBehind.
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public GuiCodeBehind() {
		this.theViewModel = new ViewModel();
	}

	/**
	 * Initializes the GUI components, binding them to the view model properties
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	@FXML
	public void initialize() {
		this.unitTextField.textProperty().bindBidirectional(this.theViewModel.getUnitTextProperty());
		this.errorMessageLabel.textProperty().bindBidirectional(this.theViewModel.getErrorMessageTextProperty());
		this.lengthTextField.textProperty().bindBidirectional(this.theViewModel.getLengthTextProperty());
		this.outputTextArea.textProperty().bindBidirectional(this.theViewModel.getOutputTextProperty());
	}
}
