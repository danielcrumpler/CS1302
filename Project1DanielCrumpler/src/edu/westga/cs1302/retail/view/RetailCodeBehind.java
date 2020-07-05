package edu.westga.cs1302.retail.view;

import java.io.File;

import edu.westga.cs1302.retail.model.Department;
import edu.westga.cs1302.retail.model.Product;
import edu.westga.cs1302.retail.viewmodel.RetailViewModel;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
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
	private ObjectProperty<Department> selectedDepartment;

	@FXML
	private AnchorPane pane;
	
    @FXML
    private Pane productPane;
    
    @FXML
    private Pane summaryPane;

	@FXML
	private ComboBox<Department> departmentsComboBox;
	
	@FXML
	private TextField departmentNameTextField;
	
	@FXML
	private Button addDepartmentButton;
	
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
    private Label upcErrorLabel;

    @FXML
    private Label descriptionErrorLabel;

    @FXML
    private Label revenueErrorLabel;

    @FXML
    private Label quantityErrorLabel;

	@FXML
	private Button addButton;

	@FXML
	private Button updateButton;

	@FXML
	private ListView<Product> productsListView;

	@FXML
	private Button deleteButton;

	@FXML
    private RadioButton revenueRadioButton;

    @FXML
    private TextField revenueRangeTextField;
    
    @FXML
    private Label revenueRangeErrorLabel;
    
    @FXML
    private RadioButton productRadioButton;
    
    @FXML
    private TextField searchTermTextField;
    
    @FXML
    private Label searchTermErrorLabel;
    
    @FXML
    private CheckBox allDepartmentsCheckBox;

    @FXML
    private TextField descriptionSearchTextField;
	
	private ToggleGroup radioButtonGroup;
	
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
		this.selectedDepartment = new SimpleObjectProperty<Department>();
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
		this.setupListenerForDepartmentsComboBox();
		this.setupListenerForOutdatedSummary();
		this.setupRadioButtons();

		this.initializeUI();
	}

	private void initializeUI() {
		this.productPane.disableProperty().set(true);
		this.summaryPane.disableProperty().set(true);
		this.selectedProduct.set(null);
		this.summaryTextArea.setStyle("-fx-font-family: monospace");
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
	
	private void setupListenerForDepartmentsComboBox() {
		this.departmentsComboBox.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldDepartment, newDepartment) -> {
					if (newDepartment != null) {
						this.selectedDepartment.set(newDepartment);
						this.productPane.disableProperty().set(false);
						this.summaryPane.disableProperty().set(false);
					} else if (oldDepartment == null) {
						this.productPane.disableProperty().set(true);
						this.summaryPane.disableProperty().set(true);
					}
					this.theViewModel.resetFormFields();
				});
	}
	
	private void setupListenerForOutdatedSummary() {
		this.theViewModel.outdatedSummaryProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue) {
				this.onGenerateReport();
			}
		});
	}
	
	private void setupRadioButtons() {
		this.radioButtonGroup = new ToggleGroup();
		this.revenueRadioButton.setToggleGroup(this.radioButtonGroup);
		this.revenueRadioButton.setSelected(true);
		this.productRadioButton.setToggleGroup(this.radioButtonGroup);
		
		this.revenueRadioButton.selectedProperty().addListener((observable, oldSelected, nowSelected) -> {
			if (!nowSelected) {
				this.revenueRangeTextField.disableProperty().set(true);
				this.clearSummaryReportErrorLabels();
			} else {
				this.revenueRangeTextField.disableProperty().set(false);
			}
		});
		
		this.productRadioButton.selectedProperty().addListener((observable, oldSelected, nowSelected) -> {
			if (!nowSelected) {
				this.searchTermTextField.disableProperty().set(true);
				this.clearSummaryReportErrorLabels();
			} else {
				this.searchTermTextField.disableProperty().set(false);
			}
		});
	}

	private void bindButtonsDisableProperty() {
		this.addDepartmentButton.disableProperty().bind(this.departmentNameTextField.textProperty().isEmpty());
		
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
		this.departmentsComboBox.itemsProperty().bind(this.theViewModel.departmentsProperty());
		this.theViewModel.selectedDepartmentProperty().bind(this.selectedDepartment);
		this.departmentNameTextField.textProperty().bindBidirectional(this.theViewModel.departmentNameProperty());
	
		this.upcTextField.textProperty().bindBidirectional(this.theViewModel.upcProperty());
		this.descriptionTextField.textProperty().bindBidirectional(this.theViewModel.descriptionProperty());
		this.revenueTextField.textProperty().bindBidirectional(this.theViewModel.revenueProperty());
		this.quantityTextField.textProperty().bindBidirectional(this.theViewModel.quantityProperty());
		this.errorLabel.textProperty().bindBidirectional(this.theViewModel.errorProperty());
		this.upcErrorLabel.textProperty().bindBidirectional(this.theViewModel.upcErrorProperty());
		this.descriptionErrorLabel.textProperty().bindBidirectional(this.theViewModel.descriptionErrorProperty());
		this.revenueErrorLabel.textProperty().bindBidirectional(this.theViewModel.revenueErrorProperty());
		this.quantityErrorLabel.textProperty().bindBidirectional(this.theViewModel.quantityErrorProperty());
		this.selectedProduct.bindBidirectional(this.theViewModel.selectedProductProperty());

		this.productsListView.itemsProperty().bind(this.theViewModel.productsProperty());
		this.summaryTextArea.textProperty().bind(this.theViewModel.summaryProperty());
	}

	
	@FXML
	private void onAddDepartment() {
		this.theViewModel.addDepartment();
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
			this.theViewModel.loadSalesData(selectedFile);
		}
	}

	@FXML
	private void onFileSave() {
		FileChooser fileChooser = new FileChooser();
		this.setFileFilters(fileChooser);

		Window owner = this.pane.getScene().getWindow();
		File selectedFile = fileChooser.showSaveDialog(owner);

		if (selectedFile != null) {
			this.theViewModel.saveSalesData(selectedFile);
		}
	}

	@FXML
	private void onGenerateReport() {
		this.clearSummaryReportErrorLabels();
		if (this.revenueRadioButton.isSelected()) {
			this.onGenerateRevenueReport();
		} else {
			this.onGenerateProductReport();
		}
		this.theViewModel.outdatedSummaryProperty().set(false);
	}

	private void onGenerateRevenueReport() {
		double revenueRange = 100;
		try {
			revenueRange = Double.parseDouble(this.revenueRangeTextField.getText().trim());
		} catch (Exception e) {
			this.revenueRangeErrorLabel.setText("invalid range value");
			return;
		}
		if (revenueRange <= 0) {
			this.revenueRangeErrorLabel.setText("value > 0 required");
			return;
		}
		
		if (!this.allDepartmentsCheckBox.isSelected()) {
			this.theViewModel.updateRevenueSummaryForDepartment(revenueRange);
		} else {
			this.theViewModel.updateRevenueSummaryForStore(revenueRange);
		}
	}
	
	private void onGenerateProductReport() {
		String searchTerm = this.searchTermTextField.getText();
		if (searchTerm == null || searchTerm.isEmpty()) {
			this.searchTermErrorLabel.setText("enter a search term");
			return;
		}
			
		if (!this.allDepartmentsCheckBox.isSelected()) {
			this.theViewModel.updateProductSummaryForDepartment(searchTerm);
		} else {
			this.theViewModel.updateProductSummaryForStore(searchTerm);
		} 
	}
	
	private void clearSummaryReportErrorLabels() {
		this.revenueRangeErrorLabel.setText("");
		this.searchTermErrorLabel.setText("");
	}
	
	private void setFileFilters(FileChooser fileChooser) {
		ExtensionFilter filter = new ExtensionFilter("Retail Sales Data", "*.rsd");
		fileChooser.getExtensionFilters().add(filter);
		filter = new ExtensionFilter("All Files", "*.*");
		fileChooser.getExtensionFilters().add(filter);
	}

}
