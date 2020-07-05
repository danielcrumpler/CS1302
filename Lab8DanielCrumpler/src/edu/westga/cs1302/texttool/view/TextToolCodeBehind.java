package edu.westga.cs1302.texttool.view;

import edu.westga.cs1302.texttool.viewmodel.TextToolViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * The Class TextToolCodeBehind.
 * 
 * @author CS1302
 */
public class TextToolCodeBehind {

	private TextToolViewModel viewmodel;

	@FXML
	private TextField titleTextField;

	@FXML
	private Label titleFeedbackLabel;

	@FXML
	private TextArea abstractTextArea;

	@FXML
	private Label wordCountLabel;

	@FXML
	private CheckBox wordCountCheckBox;

	@FXML
	private Button removeSpacesButton;

	@FXML
	private Button allUppercaseButton;

	@FXML
	private Button allLowercaseButton;

	@FXML
	private Button joinTextButton;

	@FXML
	private Button capitalizeSentencesButton;

	@FXML
	private Button extractTextFromHTMLButton;
    
    @FXML
    private void uppercaseButton(ActionEvent event) {
    	this.viewmodel.uppercaseAbstract();
    }
    
    @FXML
    private void lowercaseButton(ActionEvent event) {
    	this.viewmodel.lowercaseAbstract();
    }
    
    @FXML
    private void removeSpaces(ActionEvent event) {
    	this.viewmodel.removeSpaces();
    }
    
    @FXML
    private void joinText(ActionEvent event) {
    	this.viewmodel.joinText();
    }
    
    @FXML
    private void capitalizeSentences(ActionEvent event) {
    	this.viewmodel.capitalizeSentences();
    }
    
    @FXML
    private void extractText(ActionEvent event) {
    	this.viewmodel.extractText();
    }

	/**
	 * Instantiates a new code behind.
	 */
	public TextToolCodeBehind() {
		this.viewmodel = new TextToolViewModel();
	}

	@FXML
	void initialize() {
		this.titleFeedbackLabel.setVisible(false);
		this.wordCountLabel.setVisible(false);
		this.bindToViewModel();
		this.setupListenerForTitle();
		this.setupListenerForAbstract();
		this.setupListenerForWordCountCheckbox();
	}

	private void bindToViewModel() {
		this.titleTextField.textProperty().bindBidirectional(this.viewmodel.titleProperty());
		this.abstractTextArea.textProperty().bindBidirectional(this.viewmodel.abstractProperty());
		this.wordCountLabel.textProperty().bind(this.viewmodel.wordCountProperty());
	}
	
	private void setupListenerForTitle() {
		this.titleTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			this.viewmodel.setTitle();
			if (newValue.length() > 50) {
				this.titleTextField.setText(oldValue);
				this.titleFeedbackLabel.setVisible(true);
			} else {
				this.titleFeedbackLabel.setVisible(false);
			}
		});
	}
	
	private void setupListenerForAbstract() {
		this.abstractTextArea.textProperty().addListener((observable, oldValue, newValue) -> {
			this.viewmodel.setAbstract();
			this.viewmodel.checkWordCount();
		});
	}
	
	private void setupListenerForWordCountCheckbox() {
		this.wordCountCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue) {
				this.wordCountLabel.setVisible(true);
				this.viewmodel.checkWordCount();
			} else {
				this.wordCountLabel.setVisible(false);
			}
		});
	}
}