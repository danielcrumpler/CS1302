package edu.westga.cs1302.casino.view;

import java.util.Optional;

import edu.westga.cs1302.casino.model.Player;
import edu.westga.cs1302.casino.view.converter.ValidPositiveWholeNumberStringConverter;
import edu.westga.cs1302.casino.viewmodel.ViewModel;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;

/**
 * CodeBehind defines the "controller" for Gui.fxml.
 * 
 * @author CS 1302
 */
public class CodeBehind {
	
	private ViewModel viewmodel;
	private ObjectProperty<Player> selectedPlayer;
	private final AlertProperty alertProperty;

	private BooleanProperty registerPlayer;
	private BooleanProperty editPlayer;

	@FXML
	private AnchorPane pane;

	@FXML
	private ListView<Player> playerListView;

	@FXML
	private TextField nameTextField;

	@FXML
	private TextField ageTextField;

	@FXML
	private ComboBox<String> playerTypeComboBox;

	@FXML
	private Button registerButton;

	@FXML
	private Button editButton;

	@FXML
	private Button checkoutButton;

	@FXML
	private Button submitButton;

	@FXML
	private ComboBox<String> sortCriteriaComboBox;

	@FXML
	private Button sortByNameButton;

	@FXML
	private Button sortByBetButton;

	@FXML
	private Button sortByNeedButton;

	@FXML
	private Button feedButton;

	@FXML
	private Button drinkButton;

	@FXML
	private Button upgradeButton;

	@FXML
	private Button closeForNightButton;

	@FXML
	private Label nameLabel;

	@FXML
	private TextArea playerInfoTextArea;

	/**
	 * Creates a new CodeBehind object.
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public CodeBehind() {
		this.alertProperty = new AlertProperty();
		this.registerPlayer = new SimpleBooleanProperty(false);
		this.editPlayer = new SimpleBooleanProperty(false);

		this.viewmodel = new ViewModel(this.alertProperty);
		this.selectedPlayer = new SimpleObjectProperty<Player>();
		ObservableList<Player> players = this.viewmodel.getPlayers();
		this.playerListView = new ListView<Player>(players);
	}

	/**
	 * Initializes the GUI components, binding them to the view model properties and
	 * setting their event handlers.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 */
	@FXML
	public void initialize() {
		this.bindToViewModel();
		this.bindDisableProperties();
		this.setPlayerTypeValues();
		this.setSortCriteriaValues();
		this.setupListenersForPlayerFormComponents();
		this.setupListenersForSortCriteria();
		this.setupListenerForAlerts();
	}

	private void bindToViewModel() {
		this.nameTextField.textProperty().bindBidirectional(this.viewmodel.nameProperty());
		this.ageTextField.textProperty().bindBidirectional(this.viewmodel.ageProperty(),
				new ValidPositiveWholeNumberStringConverter());
		this.selectedPlayer.bind(this.playerListView.getSelectionModel().selectedItemProperty());

		this.selectedPlayer.bind(this.playerListView.getSelectionModel().selectedItemProperty());
		this.viewmodel.selectedPlayerProperty().bind(this.selectedPlayer);

		this.viewmodel.playerTypeProperty().bind(this.playerTypeComboBox.getSelectionModel().selectedItemProperty());
		this.playerInfoTextArea.textProperty().bindBidirectional(this.viewmodel.playerInfoProperty());

		this.playerListView.setItems(this.viewmodel.getPlayers());
	}

	private void bindDisableProperties() {
		this.nameTextField.disableProperty().bind(this.submitButton.disableProperty());
		this.ageTextField.disableProperty().bind(this.submitButton.disableProperty());
		this.playerTypeComboBox.disableProperty().bind(this.submitButton.disableProperty());

		this.registerButton.disableProperty().bind(Bindings.createBooleanBinding(() -> !this.submitButton.isDisabled(),
				this.submitButton.disableProperty()));
		this.editButton.disableProperty().bind(Bindings.createBooleanBinding(
				() -> !this.submitButton.isDisabled() || this.playerListView.getSelectionModel().isEmpty(),
				this.submitButton.disableProperty(), this.playerListView.getSelectionModel().selectedItemProperty()));
		this.checkoutButton.disableProperty().bind(Bindings.createBooleanBinding(
				() -> !this.submitButton.isDisabled() || this.playerListView.getSelectionModel().isEmpty(),
				this.submitButton.disableProperty(), this.playerListView.getSelectionModel().selectedItemProperty()));

		this.submitButton.disableProperty()
				.bind(Bindings.createBooleanBinding(
						() -> !this.registerPlayer.getValue() && !this.editPlayer.getValue(), this.registerPlayer,
						this.editPlayer));

		this.feedButton.disableProperty()
				.bind(Bindings.createBooleanBinding(() -> this.playerListView.getSelectionModel().isEmpty(),
						this.playerListView.getSelectionModel().selectedItemProperty()));
		this.drinkButton.disableProperty()
				.bind(Bindings.createBooleanBinding(() -> this.playerListView.getSelectionModel().isEmpty(),
						this.playerListView.getSelectionModel().selectedItemProperty()));
		this.upgradeButton.disableProperty()
				.bind(Bindings.createBooleanBinding(() -> this.playerListView.getSelectionModel().isEmpty(),
						this.playerListView.getSelectionModel().selectedItemProperty()));
	}

	private void setPlayerTypeValues() {
		this.playerTypeComboBox.getItems().setAll(this.viewmodel.getPlayerTypes());
		this.playerTypeComboBox.setValue(this.playerTypeComboBox.getItems().get(0));
	}

	private void setSortCriteriaValues() {
		ObservableList<String> criteria = FXCollections.observableArrayList();
		criteria.addAll("name", "bet - ascending", "bet - descending");
		this.sortCriteriaComboBox.getItems().setAll(criteria);
	}

	private void setupListenersForPlayerFormComponents() {
		this.ageTextField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d{0,3}")) {
					CodeBehind.this.ageTextField.setText(oldValue);
				}
			}
		});

		this.playerListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Player>() {
			@Override
			public void changed(ObservableValue<? extends Player> observable, Player oldPlayer, Player newPlayer) {
				if (newPlayer != null) {
					CodeBehind.this.nameTextField.setText(newPlayer.getName());
					CodeBehind.this.ageTextField.setText("" + newPlayer.getBet());
					String playerClass = newPlayer.getClass().getSimpleName();
					CodeBehind.this.playerTypeComboBox.setValue(playerClass);
					CodeBehind.this.nameLabel.setText(newPlayer.getName());
					CodeBehind.this.viewmodel.displayPlayerInfo();
				}
			}
		});
	}

	private void setupListenersForSortCriteria() {
		this.sortCriteriaComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldCriteria, String newCriteria) {
				if (newCriteria != null) {
					switch (newCriteria) {
						case "name":
							CodeBehind.this.viewmodel.sortPlayersByName();
							break;
						case "bet - ascending":
							CodeBehind.this.viewmodel.sortPlayersByBetInIncreasingOrder();
							break;
						case "bet - descending":
							CodeBehind.this.viewmodel.sortPlayersByBetInDecreasingOrder();
							break;
						default:
							break;
					}
				}
			}
		});
	}

	private void setupListenerForAlerts() {
		this.alertProperty.addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if (newValue.intValue() == AlertProperty.ERROR) {
					CodeBehind.this.showAlert(AlertType.ERROR);
				} else if (newValue.intValue() == AlertProperty.INFORMATION) {
					CodeBehind.this.showAlert(AlertType.INFORMATION);
				} else if (newValue.intValue() == AlertProperty.CONFIRMATION) {
					CodeBehind.this.showAlert(AlertType.CONFIRMATION);
				}
			}
		});
	}

	@FXML
	private void handleRegistration() {
		this.registerPlayer.setValue(true);
		this.clearPlayerForm();
	}

	@FXML
	private void handleEdit() {
		this.editPlayer.setValue(true);
	}

	@FXML
	private void handleCheckout() {
		this.viewmodel.checkoutPlayer();
		this.clearPlayerForm();
	}

	@FXML
	void handleSortByName() {
		this.viewmodel.sortPlayersByName();
	}

	@FXML
	void handleSortByAge() {
		this.viewmodel.sortPlayersByBetInIncreasingOrder();
	}

	@FXML
	void handleSortByNeed() {
		this.viewmodel.sortPlayersByBetInDecreasingOrder();
	}

	@FXML
	private void handleSubmit() {
		if (this.registerPlayer.getValue()) {
			this.viewmodel.registerPlayer();
			this.registerPlayer.setValue(false);
		} else if (this.editPlayer.getValue()) {
			this.viewmodel.editPlayer();
			this.editPlayer.setValue(false);
		}
		this.clearPlayerForm();
	}

	@FXML
	private void handleFeedPlayer() {
		this.viewmodel.feedPlayer();
		this.viewmodel.displayPlayerInfo();
	}

	@FXML
	private void handleDrinkToPlayer() {
		this.viewmodel.drinkToPlayer();
		this.viewmodel.displayPlayerInfo();
	}

	@FXML
	void handleUpgradePlayer() {
		this.viewmodel.upgradePlayer();
		this.viewmodel.displayPlayerInfo();
	}

	@FXML
	void handleCloseForNight() {
		this.viewmodel.closeForNight();
		this.viewmodel.displayPlayerInfo();
	}

	/**
	 * Show error alert dialog.
	 */
	private void showAlert(Alert.AlertType alertType) {
		Alert alert = new Alert(alertType);
		Window owner = this.pane.getScene().getWindow();
		alert.initOwner(owner);
		if (!this.alertProperty.getTitle().isEmpty()) {
			alert.setTitle(this.alertProperty.getTitle());
		}
		alert.setHeaderText(this.alertProperty.getHeader());
		alert.setContentText(this.alertProperty.getContent());
		Optional<ButtonType> alertResult = alert.showAndWait();
		if (alertType == AlertType.CONFIRMATION && alertResult.get() == ButtonType.CANCEL) {
			this.alertProperty.setResult("cancel");
		} else {
			this.alertProperty.setResult("ok");
		}
		this.alertProperty.setType(AlertProperty.NO_ALERT);
	}

	private void clearPlayerForm() {
		this.nameTextField.setText("");
		this.ageTextField.setText("0");
		this.playerTypeComboBox.getSelectionModel().select(0);
		this.playerListView.getSelectionModel().clearSelection();
	}
}