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
        FXUtility.setUpTable(tableCustomers);
        tableCustomers.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    selectedCustomer = newValue;
                    fillCustomerFields();
                });
        reset();
    }

    @Override
    public void reset() {
        refreshTable();
    }

    private void fillCustomerFields() {
        if (selectedCustomer != null) {
            textFieldName.setText(selectedCustomer.getName());
            textFieldPhone.setText(selectedCustomer.getPhone());
            textFieldAddress.setText(selectedCustomer.getAddress());
        } else {
            clear();
        }
    }

    @FXML
    private void refreshTable() {
        tableCustomers.setItems(customerDAO.getAll());
        tableCustomers.getSelectionModel().clearSelection();
    }

    @FXML
    private void clear() {
        selectedCustomer = null;
        textFieldName.clear();
        textFieldPhone.clear();
        textFieldAddress.clear();
    }

    @FXML
    private void createCustomer() {
        var customer = new CustomerEntity();
        customer.setName(textFieldName.getText());
        customer.setPhone(textFieldPhone.getText());
        customer.setAddress(textFieldAddress.getText());
        if (customerDAO.insert(customer)) {
            refreshTable();
        }
    }

    @FXML
    private void updateCustomer() {
        if (selectedCustomer == null) {
            Prompter.warning("No customer selected!");
            return;
        }
        // edit the selected customer then update it using the DAO
        selectedCustomer.setName(textFieldName.getText());
        selectedCustomer.setPhone(textFieldPhone.getText());
        selectedCustomer.setAddress(textFieldAddress.getText());
        if (customerDAO.update(selectedCustomer)) {
            refreshTable();
        }
    }

    @FXML
    private void deleteCustomer() {
        if (selectedCustomer == null) {
            Prompter.warning("No customer selected!");
            return;
        }
        if (customerDAO.delete(selectedCustomer)) {
            refreshTable();
        }
    }

}
