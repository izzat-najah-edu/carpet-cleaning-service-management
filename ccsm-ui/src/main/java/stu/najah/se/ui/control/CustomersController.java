package stu.najah.se.ui.control;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import stu.najah.se.core.dao.CustomerDAO;
import stu.najah.se.core.entity.CustomerEntity;
import stu.najah.se.ui.Controller;
import stu.najah.se.ui.Prompter;

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
        FXUtility.setUpTable(tableCustomers);
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
        refreshTable();
        clearTextFields();
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
        try {
            customerDAO.insert(customer);
            refreshTableThenFields();
        } catch (Exception e) {
            Prompter.error(e);
        }
    }

    @FXML
    private void updateCustomer() {
        if (ensureCustomerSelected()) {
            textFieldsToCustomer(selectedCustomer);
            try {
                customerDAO.update(selectedCustomer);
                refreshTableThenFields();
            } catch (Exception e) {
                Prompter.error(e);
            }
        }
    }

    @FXML
    private void deleteCustomer() {
        if (ensureCustomerSelected()) {
            try {
                customerDAO.delete(selectedCustomer);
                refreshTableThenFields();
            } catch (Exception e) {
                Prompter.error(e);
            }
        }
    }

    private boolean ensureCustomerSelected() {
        if (selectedCustomer == null) {
            Prompter.warning(FXUtility.NO_SELECTED_PRODUCT_MESSAGE);
            return false;
        }
        return true;
    }

    private void refreshTable() {
        tableCustomers.setItems(FXCollections.observableArrayList(customerDAO.getAll()));
        tableCustomers.getSelectionModel().clearSelection();
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
