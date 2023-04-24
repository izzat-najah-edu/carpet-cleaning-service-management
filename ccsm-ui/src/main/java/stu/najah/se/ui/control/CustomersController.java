package stu.najah.se.ui.control;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import stu.najah.se.core.EntityListener;
import stu.najah.se.core.ServiceManager;
import stu.najah.se.core.entity.CustomerEntity;
import stu.najah.se.core.service.CustomerService;
import stu.najah.se.ui.Controller;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomersController
        implements Controller, Initializable, EntityListener<CustomerEntity> {

    private CustomerService customerService;

    @FXML
    private TableView<CustomerEntity> tableCustomers;

    @FXML
    private TextField textFieldAddress;

    @FXML
    private TextField textFieldName;

    @FXML
    private TextField textFieldPhone;

    @FXML
    private TextField textFieldEmail;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        customerService = ServiceManager.getCustomerService();
        customerService.watchCustomer(this);
        FXUtility.setUpTable(tableCustomers);
        tableCustomers.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> customerService.selectCustomer(newValue));
    }

    @Override
    public void reset() {
        refreshTable();
    }

    @Override
    public void onEntityChanged(CustomerEntity newEntity) {
        textFieldName.setText(newEntity.getName());
        textFieldPhone.setText(newEntity.getPhone());
        textFieldAddress.setText(newEntity.getAddress());
        textFieldEmail.setText(newEntity.getEmail());
    }

    @Override
    public void onEntityCleared() {
        textFieldName.clear();
        textFieldPhone.clear();
        textFieldAddress.clear();
        textFieldEmail.clear();
    }

    @FXML
    private void refreshTable() {
        tableCustomers.setItems(FXCollections.observableArrayList(customerService.getAllCustomers()));
        tableCustomers.getSelectionModel().clearSelection();
    }

    @FXML
    private void clearCustomer() {
        customerService.clearCustomer();
    }

    @FXML
    private void createCustomer() {
        var customer = createCustomerFromTextFields();
        customerService.createAndSelectCustomer(customer);
        refreshTable();
    }

    @FXML
    private void updateCustomer() {
        var customer = createCustomerFromTextFields();
        customerService.updateCustomer(customer);
        refreshTable();
    }

    @FXML
    private void deleteCustomer() {
        customerService.deleteCustomer();
        refreshTable();
    }

    private CustomerEntity createCustomerFromTextFields() {
        var customer = new CustomerEntity();
        customer.setName(textFieldName.getText());
        customer.setPhone(textFieldPhone.getText());
        customer.setAddress(textFieldAddress.getText());
        customer.setEmail(textFieldEmail.getText());
        return customer;
    }
}
