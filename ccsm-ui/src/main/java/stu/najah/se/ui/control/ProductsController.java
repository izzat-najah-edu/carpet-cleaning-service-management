package stu.najah.se.ui.control;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import stu.najah.se.core.dao.CustomerDAO;
import stu.najah.se.core.dao.ProductDAO;
import stu.najah.se.core.entity.CustomerEntity;
import stu.najah.se.core.entity.ProductEntity;
import stu.najah.se.ui.Controller;
import stu.najah.se.ui.Prompter;

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
        FXUtility.setUpTable(tableProducts);
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
        listCustomers.setItems(FXCollections.observableArrayList(customerDAO.getAll()));
        listCustomers.getSelectionModel().clearSelection();
    }

    @FXML
    private void refreshTableThenFields() {
        if (selectedCustomer != null) {
            tableProducts.setItems(FXCollections.observableArrayList(
                    productDAO.getAll(selectedCustomer.getId())
            ));
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
            Prompter.warning(FXUtility.NO_SELECTED_CUSTOMER_MESSAGE);
            return;
        }
        var product = new ProductEntity();
        product.setCustomerId(selectedCustomer.getId());
        textFieldsToProduct(product);
        try {
            productDAO.insert(product);
            refreshTableThenFields();
        } catch (Exception e) {
            Prompter.error(e);
        }
    }

    @FXML
    private void updateProduct() {
        if (selectedCustomer == null) {
            Prompter.warning(FXUtility.NO_SELECTED_CUSTOMER_MESSAGE);
            return;
        }
        if (selectedProduct == null) {
            Prompter.warning(FXUtility.NO_SELECTED_PRODUCT_MESSAGE);
            return;
        }
        textFieldsToProduct(selectedProduct);
        try {
            productDAO.update(selectedProduct);
            refreshTableThenFields();
        } catch (Exception e) {
            Prompter.error(e);
        }
    }

    @FXML
    private void deleteProduct() {
        if (selectedCustomer == null) {
            Prompter.warning(FXUtility.NO_SELECTED_CUSTOMER_MESSAGE);
            return;
        }
        if (selectedProduct == null) {
            Prompter.warning(FXUtility.NO_SELECTED_PRODUCT_MESSAGE);
            return;
        }
        try {
            productDAO.delete(selectedProduct);
            refreshTableThenFields();
        } catch (Exception e) {
            Prompter.error(e);
        }
    }

    @FXML
    private void searchCustomer() {
        listCustomers.setItems(FXCollections.observableArrayList(
                customerDAO.getAllLike(textFieldName.getText())
        ));
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
