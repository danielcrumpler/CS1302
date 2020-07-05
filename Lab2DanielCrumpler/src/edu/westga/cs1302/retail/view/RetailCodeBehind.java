package edu.westga.cs1302.retail.view;

import java.io.File;

import edu.westga.cs1302.retail.model.Product;
import edu.westga.cs1302.retail.viewmodel.RetailViewModel;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * MenuReviewCodeBehind defines the "controller" for RetailGui.fxml.
 * 
 * @author CS1302
 */
public class RetailCodeBehind {
	private RetailViewModel theViewModel;
	private ObjectProperty<Product> selectedProduct;

	@FXML
	private AnchorPane pane;

	@FXML
	private TextField upcTextField;

	@FXML
	private TextField descriptionTextField;

	@FXML
	private TextField revenueTextField;

	@FXML
	private TextField quantityTextField;

	@FXML
	private Label errorLabel;

	@FXML
	private Button addButton;

	@FXML
	private Button updateButton;

	@FXML
	private ListView<Product> productsListView;

	@FXML
	private Button deleteButton;

	@FXML
	private TextArea summaryTextArea;

	/**
	 * Instantiates a new retail code behind.
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public RetailCodeBehind() {
		this.theViewModel = new RetailViewModel();
		this.selectedProduct = new SimpleObjectProperty<Product>();
	}

	/**
	 * Initializes the GUI components, binding them to the view model properties
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	@FXML
	public void initialize() {
		this.bindComponentsToViewModel();
		this.bindButtonsDisableProperty();
		this.setupListenerForListView();
		this.setupListenersForValidation();

		this.initializeUI();
	}

	private void initializeUI() {
		this.selectedProduct.set(null);
	}

	private void setupListenersForValidation() {
		this.revenueTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				if (newValue.startsWith(".")) {
					newValue = "0" + newValue;
					RetailCodeBehind.this.revenueTextField.setText(newValue);
				}
				if (newValue.startsWith("-.")) {
					newValue = "-0" + newValue.substring(1);
					RetailCodeBehind.this.revenueTextField.setText(newValue);
				}
				if (!newValue.matches("-?[0-9]*([.]\\d{0,2})?")) {
					RetailCodeBehind.this.revenueTextField.setText(oldValue);
				}
			}
		});

		this.quantityTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				if (!newValue.matches("-?[0-9]*")) {
					RetailCodeBehind.this.quantityTextField.setText(oldValue);
				}
			}
		});
	}

	private void setupListenerForListView() {
		this.productsListView.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldProduct, newProduct) -> {
					if (newProduct != null) {
						this.upcTextField.textProperty().set(newProduct.getUpc());
						this.descriptionTextField.textProperty().set(newProduct.getDescription());

						Double revenue = newProduct.getRevenue();
						this.revenueTextField.textProperty().set(revenue.toString());

						Integer quantity = newProduct.getQuantitySold();
						this.quantityTextField.textProperty().set(quantity.toString());

						this.selectedProduct.set(newProduct);
					}
				});
	}

	private void bindButtonsDisableProperty() {
		BooleanBinding productUnselectedBinding = Bindings
				.isNull(this.productsListView.getSelectionModel().selectedItemProperty());
		BooleanBinding emptyFormFieldsBinding = Bindings.isEmpty(this.upcTextField.textProperty())
				.or(this.descriptionTextField.textProperty().isEmpty())
				.or(this.quantityTextField.textProperty().isEmpty()).or(this.revenueTextField.textProperty().isEmpty());

		this.deleteButton.disableProperty().bind(productUnselectedBinding);
		this.updateButton.disableProperty().bind(productUnselectedBinding.or(emptyFormFieldsBinding));

		this.addButton.disableProperty().bind(emptyFormFieldsBinding);
	}

	private void bindComponentsToViewModel() {
		this.upcTextField.textProperty().bindBidirectional(this.theViewModel.upcProperty());
		this.descriptionTextField.textProperty().bindBidirectional(this.theViewModel.descriptionProperty());
		this.revenueTextField.textProperty().bindBidirectional(this.theViewModel.revenueProperty());
		this.quantityTextField.textProperty().bindBidirectional(this.theViewModel.quantityProperty());
		this.errorLabel.textProperty().bindBidirectional(this.theViewModel.errorProperty());
		this.selectedProduct.bindBidirectional(this.theViewModel.selectedProductProperty());

		this.productsListView.itemsProperty().bind(this.theViewModel.productsProperty());
		this.summaryTextArea.textProperty().bind(this.theViewModel.summaryProperty());
	}

	@FXML
	private void onAddProduct() {
		this.theViewModel.addProduct();
	}

	@FXML
	private void onUpdateProduct() {
		this.theViewModel.updateProduct();
	}

	@FXML
	private void onDeleteProduct() {
		this.theViewModel.deleteProduct();
	}

	@FXML
	private void onFileOpen() {
		FileChooser fileChooser = new FileChooser();
		this.setFileFilters(fileChooser);

		Window owner = this.pane.getScene().getWindow();
		File selectedFile = fileChooser.showOpenDialog(owner);

		if (selectedFile != null) {
			this.theViewModel.loadRetailSalesData(selectedFile);
		}
	}

	@FXML
	private void onFileSave() {
		FileChooser fileChooser = new FileChooser();
		this.setFileFilters(fileChooser);

		Window owner = this.pane.getScene().getWindow();
		File selectedFile = fileChooser.showSaveDialog(owner);

		if (selectedFile != null) {
			this.theViewModel.saveRetailSalesData(selectedFile);
		}
	}

	private void setFileFilters(FileChooser fileChooser) {
		ExtensionFilter filter = new ExtensionFilter("Retail Sales Data", "*.rsd");
		fileChooser.getExtensionFilters().add(filter);
		filter = new ExtensionFilter("All Files", "*.*");
		fileChooser.getExtensionFilters().add(filter);
	}

}
