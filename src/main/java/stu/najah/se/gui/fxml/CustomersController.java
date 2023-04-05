package stu.najah.se.gui.fxml;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;
import stu.najah.se.Navigator;
import stu.najah.se.gui.Prompter;
import stu.najah.se.sql.dao.CustomerDAO;
import stu.najah.se.sql.dao.ProductDAO;
import stu.najah.se.sql.entity.CustomerEntity;
import stu.najah.se.sql.entity.ProductEntity;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomersController
        implements Controller, Initializable {

    @FXML
    private ListView<CustomerEntity> t2listCustomers;

    @FXML
    private Tab tabAll;

    @FXML
    private Tab tabProducts;

    @FXML
    private TableView<CustomerEntity> t1tableCustomers;

    @FXML
    private TableView<ProductEntity> t2tableProducts;

    @FXML
    private TextField t1textFieldAddress;

    @FXML
    private TextField t2textFieldDescription;

    @FXML
    private TextField t1textFieldName;

    @FXML
    private TextField t2textFieldName;

    @FXML
    private TextField t1textFieldPhone;

    @FXML
    private TextField t2textFieldSpecialTreatment;

    @FXML
    private Label t2labelCustomer;

    private final CustomerDAO customerDAO = new CustomerDAO();
    private final ProductDAO productDAO = new ProductDAO();

    private CustomerEntity t1selectedCustomer = null;
    private CustomerEntity t2selectedCustomer = null;
    private ProductEntity t2selectedProduct = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // tab Edit
        Controller.setUpTable(t1tableCustomers);
        t1tableCustomers.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    t1selectedCustomer = newValue;
                    t1fill();
                });
        t1refreshTable();
        // tab Products
        Controller.setUpTable(t2tableProducts);
        t2listCustomers.setCellFactory(new Callback<>() {
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
        t2tableProducts.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    t2selectedProduct = newValue;
                    t2fill();
                }
        );
        t2listCustomers.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    t2selectedCustomer = newValue;
                    t2labelCustomer.setText(
                            t2selectedCustomer == null ? null : t2selectedCustomer.getName()
                    );
                    t2refreshTable();
                });
        t2refreshList();
    }

    @Override
    public void reset() {
        t1selectedCustomer = null;
        t2selectedCustomer = null;
        t2selectedProduct = null;
        t1refreshTable();
        t2refreshList();
        t2refreshTable();
        t1clear();
        t2clear();
    }

    void t1fill() {
        if (t1selectedCustomer != null) {
            t1textFieldName.setText(t1selectedCustomer.getName());
            t1textFieldPhone.setText(t1selectedCustomer.getPhone());
            t1textFieldAddress.setText(t1selectedCustomer.getAddress());
        } else {
            t1clear();
        }
    }

    void t2fill() {
        if (t2selectedProduct != null) {
            t2textFieldDescription.setText(t2selectedProduct.getDescription());
            t2textFieldSpecialTreatment.setText(t2selectedProduct.getSpecialTreatment());
        } else {
            t2clear();
        }
    }

    @FXML
    void t1refreshTable() {
        t1tableCustomers.setItems(customerDAO.getAll());
        t1tableCustomers.getSelectionModel().clearSelection();
        // implicitly calls clear()
    }

    @FXML
    void t2refreshList() {
        t2textFieldName.clear();
        t2listCustomers.setItems(customerDAO.getAll());
        t2listCustomers.getSelectionModel().clearSelection();
    }

    @FXML
    void t2refreshTable() {
        if (t2selectedCustomer != null) {
            t2tableProducts.setItems(productDAO.getAll(t2selectedCustomer.getId()));
            t2tableProducts.getSelectionModel().clearSelection();
        } else {
            t2tableProducts.setItems(FXCollections.observableArrayList());
        }
        // implicitly calls clear2()
    }

    @FXML
    void t1clear() {
        t1selectedCustomer = null;
        t1textFieldName.clear();
        t1textFieldPhone.clear();
        t1textFieldAddress.clear();
    }

    @FXML
    void t2clear() {
        t2selectedProduct = null;
        t2textFieldDescription.clear();
        t2textFieldSpecialTreatment.clear();
    }

    @FXML
    void t1createCustomer() {
        var customer = new CustomerEntity();
        customer.setName(t1textFieldName.getText());
        customer.setPhone(t1textFieldPhone.getText());
        customer.setAddress(t1textFieldAddress.getText());
        customerDAO.insert(customer);
        t1refreshTable();
    }

    @FXML
    void t1updateCustomer() {
        if (t1selectedCustomer == null) {
            Prompter.warning("No customer selected!");
            return;
        }
        // edit the selected customer then update it using the DAO
        t1selectedCustomer.setName(t1textFieldName.getText());
        t1selectedCustomer.setPhone(t1textFieldPhone.getText());
        t1selectedCustomer.setAddress(t1textFieldAddress.getText());
        customerDAO.update(t1selectedCustomer);
        t1refreshTable();
    }

    @FXML
    void t1deleteCustomer() {
        if (t1selectedCustomer == null) {
            Prompter.warning("No customer selected!");
            return;
        }
        customerDAO.delete(t1selectedCustomer);
        t1refreshTable();
    }

    @FXML
    void t2searchCustomer() {
        t2listCustomers.setItems(customerDAO.getAll(t2textFieldName.getText()));
    }

    @FXML
    void t2createProduct() {
        if (t2selectedCustomer == null) {
            Prompter.warning("No customer selected!");
            return;
        }
        var product = new ProductEntity();
        product.setCustomerId(t2selectedCustomer.getId());
        product.setDescription(t2textFieldDescription.getText());
        product.setSpecialTreatment(t2textFieldSpecialTreatment.getText());
        productDAO.insert(product);
        t2refreshTable();
    }

    @FXML
    void t2updateProduct() {
        if (t2selectedCustomer == null) {
            Prompter.warning("No customer selected!");
            return;
        }
        if (t2selectedProduct == null) {
            Prompter.warning("No product selected!");
            return;
        }
        t2selectedProduct.setDescription(t2textFieldDescription.getText());
        t2selectedProduct.setSpecialTreatment(t2textFieldSpecialTreatment.getText());
        productDAO.update(t2selectedProduct);
        t2refreshTable();
    }

    @FXML
    void t2deleteProduct() {
        if (t2selectedCustomer == null) {
            Prompter.warning("No customer selected!");
            return;
        }
        if (t2selectedProduct == null) {
            Prompter.warning("No product selected!");
            return;
        }
        productDAO.delete(t2selectedProduct);
        t2refreshTable();
    }
}
