package stu.najah.se.gui.control;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import stu.najah.se.data.dao.CustomerDAO;
import stu.najah.se.data.entity.CustomerEntity;
import stu.najah.se.gui.Controller;
import stu.najah.se.gui.Prompter;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomersController
        implements Controller, Initializable {

    @FXML
    private TableView<CustomerEntity> tableCustomers;

    @FXML
    private TextField textFieldAddress;

    @FXML
    private TextField textFieldName;

    @FXML
    private TextField textFieldPhone;

    private final CustomerDAO customerDAO = new CustomerDAO();

    private CustomerEntity selectedCustomer = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Utility.setUpTable(tableCustomers);
        tableCustomers.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    customerToTextFields(selectedCustomer = newValue);
                });
    }

    @Override
    public void reset() {
        refreshTableThenFields();
    }

    @FXML
    private void refreshTableThenFields() {
        tableCustomers.setItems(customerDAO.getAll());
        tableCustomers.getSelectionModel().clearSelection();
    }

    @FXML
    private void clearCustomer() {
        selectedCustomer = null;
        clearTextFields();
    }

    @FXML
    private void createCustomer() {
        var customer = new CustomerEntity();
        textFieldsToCustomer(customer);
        if (customerDAO.insert(customer)) {
            refreshTableThenFields();
        }
    }

    @FXML
    private void updateCustomer() {
        if (selectedCustomer == null) {
            Prompter.warning(Utility.NO_SELECTED_CUSTOMER_MESSAGE);
            return;
        }
        // edit the selected customer then update it using the DAO
        textFieldsToCustomer(selectedCustomer);
        if (customerDAO.update(selectedCustomer)) {
            refreshTableThenFields();
        }
    }

    @FXML
    private void deleteCustomer() {
        if (selectedCustomer == null) {
            Prompter.warning(Utility.NO_SELECTED_CUSTOMER_MESSAGE);
            return;
        }
        if (customerDAO.delete(selectedCustomer)) {
            refreshTableThenFields();
        }
    }

    private void clearTextFields() {
        textFieldName.clear();
        textFieldPhone.clear();
        textFieldAddress.clear();
    }

    private void textFieldsToCustomer(CustomerEntity customer) {
        customer.setName(textFieldName.getText());
        customer.setPhone(textFieldPhone.getText());
        customer.setAddress(textFieldAddress.getText());
    }

    private void customerToTextFields(CustomerEntity customer) {
        if (customer != null) {
            textFieldName.setText(customer.getName());
            textFieldPhone.setText(customer.getPhone());
            textFieldAddress.setText(customer.getAddress());
        } else {
            clearTextFields();
        }
    }

}
