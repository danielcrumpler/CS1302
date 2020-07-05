package edu.westga.cs1302.project3.view;

import java.io.File;
import edu.westga.cs1302.project3.viewmodel.StoryMakerViewModel;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.stage.FileChooser.ExtensionFilter;
import storyreader.util.ColorUtil;
import storyreader.util.Page;

/**
 * The class StoryMakerGuiCodeBehind
 * 
 * @author Daniel Crumpler
 */
public class StoryMakerGuiCodeBehind {

	private static final String ALL_FILES_KEY = "*.*";

	private static final String ALL_FILES = "All Files";

	private StoryMakerViewModel viewModel;

	@FXML
	private AnchorPane pane;

	@FXML
	private MenuBar menuBar;

	@FXML
	private Menu fileMenu;

	@FXML
	private MenuItem saveMenuItem;

	@FXML
	private MenuItem loadMenuItem;

	@FXML
	private Menu toolsMenu;

	@FXML
	private MenuItem displayStoryMenuItem;

	@FXML
	private MenuItem wordCountMenuItem;

	@FXML
	private Menu helpMenu;

	@FXML
	private MenuItem aboutMenuItem;

	@FXML
	private TextField titleTextField;

	@FXML
	private TextField authorTextField;

	@FXML
	private ImageView imageView;

	@FXML
	private TextArea storyTextArea;

	@FXML
	private Button deletePageButton;

	@FXML
	private Button addPageButton;

	@FXML
	private ListView<Page> listView;

	@FXML
	private Button nextPageButton;

	@FXML
	private Button previousPageButton;

	@FXML
	private Button findSynonymsButton;

	@FXML
	private Button movePageButton;

	@FXML
	private TextArea synonymTextArea;

	@FXML
	private ComboBox<String> fontComboBox;

	@FXML
	private TextField fontSizeTextField;

	@FXML
	private ColorPicker textColorPicker;

	@FXML
	private ColorPicker backgroundColorPicker;

	/**
	 * Instantiates a new password manager GUI code behind.
	 */
	public StoryMakerGuiCodeBehind() {
		this.viewModel = new StoryMakerViewModel();

	}

	@FXML
	private void initialize() {
		this.bindToViewModel();
		this.setupSelectionHandlers();
		this.setupNewStory();
		this.setupListenersForDisplaySettings();
		this.buttonBinding();
		this.viewModel.loadSynonyms();
	}

	private void setupNewStory() {
		this.viewModel.addDefaultPage();
		this.listView.getSelectionModel().select(0);
	}

	private void bindToViewModel() {
		this.authorTextField.textProperty().bindBidirectional(this.viewModel.getAuthorProperty());
		this.titleTextField.textProperty().bindBidirectional(this.viewModel.getTitleProperty());
		this.storyTextArea.textProperty().bindBidirectional(this.viewModel.getTextProperty());
		this.listView.itemsProperty().bind(this.viewModel.pagesProperty());
		this.imageView.imageProperty().bind(this.viewModel.imageProperty());
		this.synonymTextArea.textProperty().bindBidirectional(this.viewModel.getSynonymProperty());
		Tooltip.install(this.imageView, new Tooltip("Click here to upload a new image."));
		this.viewModel.getComboProperty().bind(this.fontComboBox.getSelectionModel().selectedItemProperty());
		this.fontComboBox.itemsProperty().set(FXCollections.observableArrayList(Font.getFontNames()));
		this.fontSizeTextField.textProperty().bindBidirectional(this.viewModel.getFontSizeProperty());
		this.backgroundColorPicker.valueProperty().bindBidirectional(this.viewModel.getBackgroundColorProperty());
		this.textColorPicker.valueProperty().bindBidirectional(this.viewModel.getTextColorProperty());
	}

	private void setupSelectionHandlers() {
		this.listView.getSelectionModel().selectedItemProperty().addListener((observable, oldPage, newPage) -> {
			if (newPage != null) {
				Page page = this.viewModel.getPage(this.listView.getSelectionModel().getSelectedIndex() + 1);
				this.viewModel.setPageNumber(this.listView.getSelectionModel().getSelectedIndex() + 1);
				this.storyTextArea.textProperty().set(page.getText());
				this.viewModel.getTextProperty().set(page.getText());
				this.viewModel.getImage();
				this.listView.refresh();
				this.buttonBinding();
			}
		});
		this.storyTextArea.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null && this.listView.itemsProperty().get().size() != 0
					&& !this.storyTextArea.textProperty().get().isEmpty()) {
				Page page = this.viewModel.getPage(this.viewModel.getPageNumber());
				page.setText(newValue);
				this.listView.refresh();
			}
		});
		this.authorTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null && !newValue.isEmpty()) {
				this.viewModel.getStory().setAuthor(this.authorTextField.textProperty().get());
			}
		});
		this.titleTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null && !newValue.isEmpty()) {
				this.viewModel.getStory().setTitle(this.titleTextField.textProperty().get());
			}
		});
	}

	private void setupListenersForDisplaySettings() {
		this.fontComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldFont, newFont) -> {
			if (newFont != null) {
				this.storyTextArea.setFont(Font.font(newFont));
				this.viewModel.getStory().getDisplaySetting().setFont(newFont);
			}
		});
		this.backgroundColorPicker.valueProperty().addListener((observable, oldColor, newColor) -> {
			if (newColor != null) {
				Background background = new Background(
						new BackgroundFill(this.backgroundColorPicker.getValue(), CornerRadii.EMPTY, Insets.EMPTY));
				this.viewModel.getStory().getDisplaySetting().setBackgroundColor(this.backgroundColorPicker.getValue());
				this.storyTextArea.setBackground(background);
				this.listView.setBackground(background);
			}
		});
		this.textColorPicker.valueProperty().addListener((observable, oldColor, newColor) -> {
			if (newColor != null) {
				String textColorStyleRule = "-fx-text-fill: "
						+ ColorUtil.toHexWebString(this.textColorPicker.getValue()) + ";";
				this.viewModel.getStory().getDisplaySetting().setTextColor(this.textColorPicker.getValue());
				this.storyTextArea.setStyle(textColorStyleRule);
			}
		});
		this.listenerForFontSize();
	}

	private void listenerForFontSize() {
		this.fontSizeTextField.textProperty().addListener((observable, oldValue, newValue) -> {		
			if (newValue != null && !newValue.matches("/D") && !newValue.isEmpty()) {
				int fontSize = 0;
				try {
					fontSize = Integer.parseInt(newValue);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (fontSize > 0) {
					this.storyTextArea.setFont(Font.font(this.viewModel.getStory().getDisplaySetting().getFont(),
							FontWeight.NORMAL, fontSize));
					this.viewModel.getStory().getDisplaySetting().setFontSize(fontSize);
				}
			}
		});
	}

	@FXML
	private void addPage(ActionEvent event) {
		try {
			if (!this.viewModel.addDefaultPage()) {
				System.out.println("default page failed to add");
			} else {
				this.listView.getSelectionModel().selectLast();
				System.out.println("default page added");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@FXML
	private void deletePage(ActionEvent event) {
		try {
			if (!this.viewModel.deletePage()) {
				System.out.println("page failed to delete");
			} else {
				this.clearForms();
				System.out.println("page deleted");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@FXML
	private void nextPage(ActionEvent event) {
		this.listView.getSelectionModel().selectNext();
	}

	@FXML
	private void previousPage(ActionEvent event) {
		this.listView.getSelectionModel().selectPrevious();
	}

	@FXML
	private void openImage(MouseEvent event) {
		FileChooser fileChooser = new FileChooser();
		this.setImageFileFilters(fileChooser);

		Window owner = this.pane.getScene().getWindow();
		File selectedFile = fileChooser.showOpenDialog(owner);

		if (selectedFile != null) {
			this.viewModel.setImage(selectedFile);
		}
	}

	private void setImageFileFilters(FileChooser fileChooser) {
		ExtensionFilter filter = new ExtensionFilter("BMP", "*.bmp");
		fileChooser.getExtensionFilters().add(filter);
		filter = new ExtensionFilter("GIF", "*.gif");
		fileChooser.getExtensionFilters().add(filter);
		filter = new ExtensionFilter("JPEG", "*.jpeg");
		fileChooser.getExtensionFilters().add(filter);
		filter = new ExtensionFilter("PNG", "*.png");
		fileChooser.getExtensionFilters().add(filter);
		filter = new ExtensionFilter(ALL_FILES, ALL_FILES_KEY);
		fileChooser.getExtensionFilters().add(filter);
	}

	@FXML
	private void loadItem(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open");
		this.setLoadFileFilters(fileChooser);
		Window owner = this.pane.getScene().getWindow();
		File selectedFile = fileChooser.showOpenDialog(owner);
		if (selectedFile != null) {
			try {
				this.viewModel.loadStory(selectedFile);
				this.fontComboBox.setValue(this.viewModel.getStory().getDisplaySetting().getFont());
				this.listView.refresh();
				this.listView.getSelectionModel().selectFirst();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void setLoadFileFilters(FileChooser fileChooser) {
		ExtensionFilter filter = new ExtensionFilter("Story files", "*.story");
		fileChooser.getExtensionFilters().add(filter);
		filter = new ExtensionFilter(ALL_FILES, ALL_FILES_KEY);
		fileChooser.getExtensionFilters().add(filter);
	}

	@FXML
	private void saveItem(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		this.setLoadFileFilters(fileChooser);

		Window owner = this.pane.getScene().getWindow();
		File selectedFile = fileChooser.showSaveDialog(owner);

		if (selectedFile != null) {
			this.viewModel.saveStory(selectedFile);
		}
	}

	@FXML
	private void aboutItem(ActionEvent event) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		Window owner = this.pane.getScene().getWindow();
		alert.initOwner(owner);
		alert.setTitle("About");
		alert.setHeaderText("StoryMaker by Daniel Crumpler");
		alert.setContentText("Version 1.0");
		alert.showAndWait();
	}

	@FXML
	private void displayStoryItem(ActionEvent event) {
		this.viewModel.displayStoryReader();
	}

	@FXML
	private void wordCount(ActionEvent event) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		Window owner = this.pane.getScene().getWindow();
		alert.initOwner(owner);
		alert.setTitle("Word Count");
		alert.setHeaderText(this.viewModel.wordCount());
		alert.showAndWait();
	}

	@FXML
	private void findSynonyms(ActionEvent event) {
		this.viewModel.getASynonym(this.storyTextArea.selectedTextProperty().get());
	}

	@FXML
	private void movePage(ActionEvent event) {
		ChoiceDialog<Page> alert = new ChoiceDialog<Page>();
		Window owner = this.pane.getScene().getWindow();
		alert.initOwner(owner);
		alert.setTitle("Move Page");
		alert.setHeaderText(
				"Move selected page '" + this.listView.getSelectionModel().getSelectedItem().toString() + "'");
		alert.setContentText("Insert page before: ");
		alert.getItems().setAll(FXCollections.observableArrayList(this.viewModel.getStory()));
		alert.showAndWait();
		int pageToMoveBefore = alert.getItems().indexOf(alert.getSelectedItem()) + 1;
		this.viewModel.movePage(pageToMoveBefore);
		this.listView.refresh();
	}

	private void buttonBinding() {		
		BooleanBinding alwaysTrueBinding = Bindings.isNotEmpty(this.listView.getItems());
		BooleanBinding emptyBinding = Bindings.isEmpty(this.listView.getItems());
		this.deletePageButton.disableProperty().bind(emptyBinding);
		this.movePageButton.disableProperty().bind(emptyBinding);
		this.nextPageButton.disableProperty().bind(emptyBinding);
		this.previousPageButton.disableProperty().bind(emptyBinding);
		this.imageView.disableProperty().bind(emptyBinding);
		
		if (this.listView.getSelectionModel().isSelected(0)) {
			this.previousPageButton.disableProperty().bind(alwaysTrueBinding);
		} else {
			this.previousPageButton.disableProperty().unbind();
		}
		if (this.listView.getSelectionModel().isSelected(this.listView.getItems().size() - 1)) {
			this.nextPageButton.disableProperty().bind(alwaysTrueBinding);
		} else {
			this.nextPageButton.disableProperty().unbind();
		}
	}

	private void clearForms() {
		this.storyTextArea.clear();
		this.fontSizeTextField.clear();
		this.fontComboBox.getSelectionModel().clearSelection();
	}

}
