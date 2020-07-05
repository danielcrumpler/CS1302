package edu.westga.cs1302.grades.view;

import edu.westga.cs1302.grades.viewmodel.ViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class CodeBehind {

	private static final String DEFAULT_LAB_VALUE = "10";
	private static final String DEFAULT_EXAM_VALUE = "100";
	private static final String DEFAULT_PROJECT_VALUE = "100";
	private static final String DEFAULT_SI_VALUE = "5";
	private static final String EMPTY_STRING_ERROR = "";

	private ViewModel viewModel;

	@FXML
	private AnchorPane examsPane;

	@FXML
	private AnchorPane projectsPane;

	@FXML
	private TextArea letterGradeTextArea;

	@FXML
	private AnchorPane labsPane;

	@FXML
	private TextField siTextField;

	@FXML
	private Label errorLabel;

	/**
	 * Initializes the GUI components, binding them to the view model properties
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	@FXML
	void initialize() {
		this.viewModel = new ViewModel();
		this.bindToViewModel();
		this.siListener();
		this.labsListener();
		this.examsListener();
		this.projectsListener();
	}

	private void labsListener() {
		for (Node node : this.labsPane.getChildren()) {
			((TextField) node).textProperty().addListener((observable, oldValue, newValue) -> {
				if (!newValue.matches("[0,4,7]|[10]?")) {
					this.errorLabel.setText("Lab grades must be 0, 4, 7, or 10");
					((TextField) node).textProperty().setValue(oldValue);
					this.errorLabel.setVisible(true);
				} else {
					this.errorLabel.setVisible(false);
				}
			});
		}
	}
	
	private void examsListener() {
		for (Node node : this.examsPane.getChildren()) {
			((TextField) node).textProperty().addListener((observable, oldValue, newValue) -> {
				if (!newValue.matches("100(\\.0{0,2}?)?$|^\\d{0,2}(\\.\\d{0,2})?")) {
					this.errorLabel.setText("Exam grades must be 0-100 with at most two decimals");
					((TextField) node).textProperty().setValue(oldValue);
					this.errorLabel.setVisible(true);
				} else {
					this.errorLabel.setVisible(false);
				}
			});
		}
	}
	
	private void projectsListener() {
		for (Node node : this.projectsPane.getChildren()) {
			((TextField) node).textProperty().addListener((observable, oldValue, newValue) -> {
				if (!newValue.matches("100(\\.0{0,2}?)?$|^\\d{0,2}(\\.\\d{0,2})?")) {
					this.errorLabel.setText("Exam grades must be 0-100 with at most two decimals");
					((TextField) node).textProperty().setValue(oldValue);
					this.errorLabel.setVisible(true);
				} else {
					this.errorLabel.setVisible(false);
				}
			});
		}
	}

	private void siListener() {
		this.siTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("[0-5]?")) {
				this.errorLabel.setText("SI grade must be 0-5");
				this.siTextField.setText(oldValue);
				this.errorLabel.setVisible(true);
			} else {
				this.errorLabel.setVisible(false);
			}
		});
	}

	private void bindToViewModel() {
		this.letterGradeTextArea.textProperty().bindBidirectional(this.viewModel.getGradeProperty());

		int index = 0;
		for (Node node : this.labsPane.getChildren()) {
			if (node instanceof TextField) {
				((TextField) node).textProperty().bindBidirectional(this.viewModel.getLabProperty(index));
				((TextField) node).textProperty().setValue(DEFAULT_LAB_VALUE);
				index++;
			}
		}

		index = 0;
		for (Node node : this.examsPane.getChildren()) {
			if (node instanceof TextField) {
				((TextField) node).textProperty().bindBidirectional(this.viewModel.getExamProperty(index));
				((TextField) node).textProperty().setValue(DEFAULT_EXAM_VALUE);
				index++;
			}
		}

		index = 0;
		for (Node node : this.projectsPane.getChildren()) {
			if (node instanceof TextField) {
				((TextField) node).textProperty().bindBidirectional(this.viewModel.getProjectProperty(index));
				((TextField) node).textProperty().setValue(DEFAULT_PROJECT_VALUE);
				index++;
			}
		}
		this.siTextField.textProperty().bindBidirectional(this.viewModel.getSiProperty());
		this.siTextField.textProperty().setValue(DEFAULT_SI_VALUE);
		this.errorLabel.textProperty().bindBidirectional(this.viewModel.getErrorProperty());
		this.errorLabel.textProperty().setValue(EMPTY_STRING_ERROR);
	}

	@FXML
	void buttonHandler(ActionEvent event) {
		this.viewModel.updateGrades();
	}

}
