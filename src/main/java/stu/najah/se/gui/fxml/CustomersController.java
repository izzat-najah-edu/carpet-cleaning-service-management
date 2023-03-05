package stu.najah.se.gui.fxml;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import stu.najah.se.Navigator;
import stu.najah.se.dao.CustomerDAO;
import stu.najah.se.gui.FXUtility;
import stu.najah.se.sql.entity.CustomerEntity;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomersController
        implements Initializable {

    @FXML
    private ListView<CustomerEntity> listCustomers2;

    @FXML
    private Tab tabEditCustomer;

    @FXML
    private Tab tabProducts;

    @FXML
    private TableView<CustomerEntity> tableCustomers;

    @FXML
    private TableView<?> tableProducts2;

    @FXML
    private TextField textFieldAddress;

    @FXML
    private TextField textFieldDescription2;

    @FXML
    private TextField textFieldName;

    @FXML
    private TextField textFieldName2;

    @FXML
    private TextField textFieldPhone;

    @FXML
    private TextField textFieldSpecialTreatment2;

    private final CustomerDAO customerDAO = new CustomerDAO();
    private CustomerEntity selectedCustomer = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXUtility.setUpTable(tableCustomers);
        tableCustomers.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    selectedCustomer = newValue;
                    fill();
                });
        refresh();
    }

    @FXML
    void refresh() {
        tableCustomers.setItems(customerDAO.getAll());
        tableCustomers.getSelectionModel().clearSelection();
        // implicitly calls clear()
    }

    @FXML
    void refresh2() {

    }

    @FXML
    void clear() {
        selectedCustomer = null;
        textFieldName.clear();
        textFieldPhone.clear();
        textFieldAddress.clear();
    }

    private void fill() {
        if (selectedCustomer != null) {
            textFieldName.setText(selectedCustomer.getName());
            textFieldPhone.setText(selectedCustomer.getPhone());
            textFieldAddress.setText(selectedCustomer.getAddress());
        } else {
            clear();
        }
    }

    @FXML
    void clear2() {

    }

    @FXML
    void createCustomer() {
        var customer = new CustomerEntity();
        customer.setName(textFieldName.getText());
        customer.setPhone(textFieldPhone.getText());
        customer.setAddress(textFieldAddress.getText());
        customerDAO.insert(customer);
        refresh();
    }

    @FXML
    void deleteCustomer() {
        if(selectedCustomer == null) {
            Navigator.prompt("No customer selected!");
            return;
        }
        customerDAO.delete(selectedCustomer);
        refresh();
    }

    @FXML
    void updateCustomer() {
        if(selectedCustomer == null) {
            Navigator.prompt("No customer selected!");
            return;
        }
        // edit the selected customer then update it using the DAO
        selectedCustomer.setName(textFieldName.getText());
        selectedCustomer.setPhone(textFieldPhone.getText());
        selectedCustomer.setAddress(textFieldAddress.getText());
        customerDAO.update(selectedCustomer);
        refresh();
    }

    @FXML
    void searchCustomer() {

    }

    @FXML
    void createProduct() {

    }

    @FXML
    void updateProduct() {

    }

    @FXML
    void deleteProduct() {

    }
}
