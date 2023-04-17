package stu.najah.se.ui.control;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import stu.najah.se.core.EntityListener;
import stu.najah.se.core.ServiceManager;
import stu.najah.se.core.entity.CustomerEntity;
import stu.najah.se.core.entity.ProductEntity;
import stu.najah.se.core.service.CustomerService;
import stu.najah.se.core.service.ProductService;
import stu.najah.se.ui.Controller;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductsController
        implements Controller, Initializable, EntityListener<ProductEntity> {

    private CustomerService customerService;

    private ProductService productService;

    @FXML
    private ListView<CustomerEntity> listCustomers;

    @FXML
    private TableView<ProductEntity> tableProducts;

    @FXML
    private TextField textFieldDescription;

    @FXML
    private TextField textFieldName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        customerService = ServiceManager.getCustomerService();
        productService = ServiceManager.getProductService();
        productService.watchProduct(this);
        FXUtility.setUpTable(tableProducts);
        tableProducts.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue == null) {
                        productService.clearProduct();
                    } else {
                        productService.selectProduct(newValue.getId());
                    }
                }
        );
        listCustomers.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    customerService.selectCustomer(newValue);
                    refreshTable();
                });
    }

    @Override
    public void onEntityChanged(ProductEntity newEntity) {
        textFieldDescription.setText(newEntity.getDescription());
    }

    @Override
    public void onEntityCleared() {
        textFieldDescription.clear();
    }

    @Override
    public void reset() {
        refreshList();
    }

    @FXML
    private void refreshList() {
        textFieldName.clear();
        listCustomers.setItems(FXCollections.observableArrayList(customerService.getAllCustomers()));
        listCustomers.getSelectionModel().clearSelection();
    }

    @FXML
    private void searchCustomer() {
        listCustomers.setItems(FXCollections.observableArrayList(
                customerService.getAllCustomersWith(textFieldName.getText())
        ));
    }

    @FXML
    private void refreshTable() {
        try {
            var list = FXCollections.observableArrayList(productService.getAllCustomerProducts());
            tableProducts.setItems(list);
        } catch (IllegalStateException e) {
            tableProducts.setItems(FXCollections.observableArrayList());
        } finally {
            tableProducts.getSelectionModel().clearSelection();
        }
    }

    @FXML
    private void clearProduct() {
        productService.clearProduct();
    }

    @FXML
    private void createProduct() {
        productService.createAndSelectProduct(new ProductEntity(
                textFieldDescription.getText()
        ));
        refreshTable();
    }

    @FXML
    private void updateProduct() {
        productService.updateProduct(new ProductEntity(
                textFieldDescription.getText()
        ));
        refreshTable();
    }

    @FXML
    private void deleteProduct() {
        productService.deleteProduct();
        refreshTable();
    }
}
