package edu.westga.cs1302.grades.view;

import edu.westga.cs1302.grades.viewmodel.ViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * Gui defines the "controller" for Gui.fxml.
 * 
 * @author Daniel Crumpler
 */
public class GuiCodeBehind {

	private ViewModel theViewModel;
	
    @FXML
    private AnchorPane labsPane;

    @FXML
    private TextField lab1TextField;

    @FXML
    private TextField examOneGradeTextField;

    @FXML
    private TextArea finalGradeTextArea;

    @FXML
    private Button calculateGradeButton;

    @FXML
    void handleCalculateGrade(ActionEvent event) {
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
		this.examOneGradeTextField.textProperty().bindBidirectional(this.theViewModel.getExam1TextProperty());
		this.finalGradeTextArea.textProperty().bindBidirectional(this.theViewModel.getFinalGradeTextProperty());
	}

}