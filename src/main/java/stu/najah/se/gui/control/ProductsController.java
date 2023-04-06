package stu.najah.se.gui.control;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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

    @FXML
    private Label labelSelectedCustomer;

    private final CustomerDAO customerDAO = new CustomerDAO();
    private final ProductDAO productDAO = new ProductDAO();

    private CustomerEntity selectedCustomer = null;
    private ProductEntity selectedProduct = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXUtility.setUpTable(tableProducts);
        listCustomers.setCellFactory(new Callback<>() {
            // this is to avoid overriding toString() of CustomerEntity class
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
                    selectedProduct = newValue;
                    refreshSelectedProduct();
                }
        );
        listCustomers.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    selectedCustomer = newValue;
                    refreshSelectedCustomer();
                    refreshTable();
                });
        reset();
    }

    @Override
    public void reset() {
        refreshList();
        refreshTable();
    }

    private void refreshSelectedProduct() {
        if (selectedProduct != null) {
            textFieldDescription.setText(selectedProduct.getDescription());
        } else {
            clear();
        }
    }

    private void refreshSelectedCustomer() {
        labelSelectedCustomer.setText(
                selectedCustomer == null ? null : selectedCustomer.getName()
        );
    }

    @FXML
    void refreshList() {
        textFieldName.clear();
        listCustomers.setItems(customerDAO.getAll());
        listCustomers.getSelectionModel().clearSelection();
    }

    @FXML
    void refreshTable() {
        if (selectedCustomer != null) {
            tableProducts.setItems(productDAO.getAll(selectedCustomer.getId()));
            tableProducts.getSelectionModel().clearSelection();
        } else {
            tableProducts.setItems(FXCollections.observableArrayList());
        }
    }

    @FXML
    void clear() {
        selectedProduct = null;
        textFieldDescription.clear();
    }

    @FXML
    void searchCustomer() {
        listCustomers.setItems(customerDAO.getAll(textFieldName.getText()));
    }

    @FXML
    void createProduct() {
        if (selectedCustomer == null) {
            Prompter.warning("No customer selected!");
            return;
        }
        var product = new ProductEntity();
        product.setCustomerId(selectedCustomer.getId());
        product.setDescription(textFieldDescription.getText());
        if (productDAO.insert(product)) {
            refreshTable();
        }
    }

    @FXML
    void updateProduct() {
        if (selectedCustomer == null) {
            Prompter.warning("No customer selected!");
            return;
        }
        if (selectedProduct == null) {
            Prompter.warning("No product selected!");
            return;
        }
        selectedProduct.setDescription(textFieldDescription.getText());
        if (productDAO.update(selectedProduct)) {
            refreshTable();
        }
    }

    @FXML
    void deleteProduct() {
        if (selectedCustomer == null) {
            Prompter.warning("No customer selected!");
            return;
        }
        if (selectedProduct == null) {
            Prompter.warning("No product selected!");
            return;
        }
        if (productDAO.delete(selectedProduct)) {
            refreshTable();
        }
    }
}
