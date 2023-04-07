package stu.najah.se.gui.control;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import stu.najah.se.data.dao.CustomerDAO;
import stu.najah.se.data.dao.ProductDAO;
import stu.najah.se.data.entity.CustomerEntity;
import stu.najah.se.data.entity.ProductEntity;
import stu.najah.se.gui.Controller;
import stu.najah.se.gui.Prompter;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductsController
        implements Controller, Initializable {

    @FXML
    private ListView<CustomerEntity> listCustomers;

    @FXML
    private TableView<ProductEntity> tableProducts;

    @FXML
    private TextField textFieldDescription;

    @FXML
    private TextField textFieldName;

    private final CustomerDAO customerDAO = new CustomerDAO();
    private final ProductDAO productDAO = new ProductDAO();

    private CustomerEntity selectedCustomer = null;
    private ProductEntity selectedProduct = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Utility.setUpTable(tableProducts);
        listCustomers.setCellFactory(new Callback<>() {
            // to avoid overriding toString() of CustomerEntity
            @Override
            public ListCell<CustomerEntity> call(ListView<CustomerEntity> param) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(CustomerEntity item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setText(null);
                        } else {
                            setText(item.getName());
                        }
                    }
                };
            }
        });
        tableProducts.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    productToTextFields(selectedProduct = newValue);
                }
        );
        listCustomers.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    selectedCustomer = newValue;
                    refreshTableThenFields();
                });
    }

    @Override
    public void reset() {
        refreshListThenTableThenFields();
    }

    @FXML
    private void refreshListThenTableThenFields() {
        clearSearchFields();
        listCustomers.setItems(customerDAO.getAll());
        listCustomers.getSelectionModel().clearSelection();
    }

    @FXML
    private void refreshTableThenFields() {
        if (selectedCustomer != null) {
            tableProducts.setItems(productDAO.getAll(selectedCustomer.getId()));
        } else {
            tableProducts.setItems(FXCollections.observableArrayList());
        }
        tableProducts.getSelectionModel().clearSelection();
    }

    @FXML
    private void clearProduct() {
        selectedProduct = null;
        clearProductTextFields();
    }

    @FXML
    private void createProduct() {
        if (selectedCustomer == null) {
            Prompter.warning(Utility.NO_SELECTED_CUSTOMER_MESSAGE);
            return;
        }
        var product = new ProductEntity();
        product.setCustomerId(selectedCustomer.getId());
        textFieldsToProduct(product);
        if (productDAO.insert(product)) {
            refreshTableThenFields();
        }
    }

    @FXML
    private void updateProduct() {
        if (selectedCustomer == null) {
            Prompter.warning(Utility.NO_SELECTED_CUSTOMER_MESSAGE);
            return;
        }
        if (selectedProduct == null) {
            Prompter.warning(Utility.NO_SELECTED_PRODUCT_MESSAGE);
            return;
        }
        textFieldsToProduct(selectedProduct);
        if (productDAO.update(selectedProduct)) {
            refreshTableThenFields();
        }
    }

    @FXML
    private void deleteProduct() {
        if (selectedCustomer == null) {
            Prompter.warning(Utility.NO_SELECTED_CUSTOMER_MESSAGE);
            return;
        }
        if (selectedProduct == null) {
            Prompter.warning(Utility.NO_SELECTED_PRODUCT_MESSAGE);
            return;
        }
        if (productDAO.delete(selectedProduct)) {
            refreshTableThenFields();
        }
    }

    @FXML
    private void searchCustomer() {
        listCustomers.setItems(customerDAO.getAll(textFieldName.getText()));
    }

    private void clearSearchFields() {
        textFieldName.clear();
    }

    private void clearProductTextFields() {
        textFieldDescription.clear();
    }

    private void textFieldsToProduct(ProductEntity product) {
        product.setDescription(textFieldDescription.getText());
    }

    private void productToTextFields(ProductEntity product) {
        if (product != null) {
            textFieldDescription.setText(product.getDescription());
        } else {
            clearProductTextFields();
        }
    }
}
