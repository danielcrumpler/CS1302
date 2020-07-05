package edu.westga.cs1302.olympics.view;

import edu.westga.cs1302.olympics.model.Location;
import edu.westga.cs1302.olympics.model.OlympicsKey;
import edu.westga.cs1302.olympics.model.OlympicsType;
import edu.westga.cs1302.olympics.viewmodel.OlympicsViewModel;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * The Class OlympicsGuiCodeBehind.
 * 
 * @author CS1302
 */
public class OlympicsGuiCodeBehind {

	private OlympicsViewModel viewModel;

	@FXML
	private TextField yearTextField;

	@FXML
	private TextField cityTextField;

	@FXML
	private TextField countryTextField;

	@FXML
	private ComboBox<OlympicsType> typeComboBox;

	@FXML
	private ListView<OlympicsKey> olympicsListView;

	@FXML
	private Button searchButton;

	@FXML
	private Button addButton;

	@FXML
	private Button updateButton;

	@FXML
	private Button removeButton;

	@FXML
	private Label errorLabel;

	/**
	 * Instantiates a new password manager gui code behind.
	 */
	public OlympicsGuiCodeBehind() {
		this.viewModel = new OlympicsViewModel();

	}

	@FXML
	private void initialize() {
		this.bindToViewModel();
		this.setupButtonDisabling();
		this.setupChangeListenerListView();
	}

	private void bindToViewModel() {
		this.yearTextField.textProperty().bindBidirectional(this.viewModel.yearProperty());
		this.cityTextField.textProperty().bindBidirectional(this.viewModel.cityProperty());
		this.countryTextField.textProperty().bindBidirectional(this.viewModel.countryProperty());
		this.viewModel.typeProperty().bind(this.typeComboBox.getSelectionModel().selectedItemProperty());
		this.olympicsListView.itemsProperty().bind(this.viewModel.listProperty());
		this.typeComboBox.itemsProperty().set(FXCollections.observableArrayList(OlympicsType.values()));
	}

	private void setupButtonDisabling() {
		BooleanBinding addUpdateBinding = this.typeComboBox.getSelectionModel().selectedItemProperty().isNull()
				.or(this.yearTextField.textProperty().isEmpty().or(this.cityTextField.textProperty().isEmpty()
						.or(this.countryTextField.textProperty().isEmpty())));
		this.addButton.disableProperty().bind(addUpdateBinding);
		this.updateButton.disableProperty()
				.bind(addUpdateBinding.or(Bindings.isEmpty(this.olympicsListView.getItems())));

		BooleanBinding searchRemoveBinding = Bindings.or(this.yearTextField.textProperty().isEmpty(),
				this.typeComboBox.getSelectionModel().selectedItemProperty().isNull());
		this.searchButton.disableProperty().bind(searchRemoveBinding);
		this.removeButton.disableProperty().bind(searchRemoveBinding);
	}

	private void setupChangeListenerListView() {
		this.olympicsListView.getSelectionModel().selectedItemProperty().addListener((observable, oldKey, newKey) -> {
			if (newKey != null) {
				this.yearTextField.setText("" + newKey.getYear());
				this.typeComboBox.getSelectionModel().select(newKey.getType());
			}
		});
	}

	@FXML
	private void handleAddOlympics() {
		try {
			if (!this.viewModel.addOlympics()) {
				this.errorLabel.setText("Olympics were not added");
			} else {
				this.clearForm();
			}
		} catch (Exception ex) {
			this.errorLabel.setText(ex.getMessage());
		}
	}

	@FXML
	private void handleUpdateOlympics() {
		try {
			if (!this.viewModel.updateOlympics()) {
				this.errorLabel.setText("Olympics were not updated");
			} else {
				this.clearForm();
			}
		} catch (Exception ex) {
			this.errorLabel.setText(ex.getMessage());
		}

	}

	@FXML
	private void handleRemoveOlympics() {
		try {
			if (!this.viewModel.deleteOlympics()) {
				this.errorLabel.setText("no Olympics to delete");
			} else {
				this.clearForm();
			}
		} catch (Exception ex) {
			this.errorLabel.setText(ex.getMessage());
		}
	}

	@FXML
	private void handleSearchOlympics() {
		try {
			Location location = this.viewModel.searchForOlympics();
			if (location == null) {
				this.errorLabel.setText("no such Olympics exist");
			} else {
				this.cityTextField.setText(location.getCity());
				this.countryTextField.setText(location.getCountry());
				this.errorLabel.setText("");
			}
		} catch (IllegalArgumentException | NullPointerException ex) {
			this.errorLabel.setText(ex.getMessage());
		}
	}

	private void clearForm() {
		this.olympicsListView.getSelectionModel().clearSelection();
		this.typeComboBox.getSelectionModel().clearSelection();
		this.yearTextField.setText("");
		this.cityTextField.setText("");
		this.countryTextField.setText("");
		this.errorLabel.setText("");
	}
}
