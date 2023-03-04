package stu.najah.se.gui.fxml;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import stu.najah.se.Navigator;
import stu.najah.se.dao.CustomerDAO;
import stu.najah.se.gui.FXUtility;
import stu.najah.se.sql.entity.CustomerEntity;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomersController
        implements Initializable {

    @FXML
    private Tab tabEditCustomer;

    @FXML
    private Tab tab2;

    @FXML
    private TableView<CustomerEntity> tableCustomers;

    @FXML
    private TableColumn<CustomerEntity, Integer> tableCustomersColId;

    @FXML
    private TableColumn<CustomerEntity, String> tableCustomersColName;

    @FXML
    private TableColumn<CustomerEntity, String> tableCustomersColPhone;

    @FXML
    private TableColumn<CustomerEntity, String> tableCustomersColAddress;

    @FXML
    private TextField textFieldName;

    @FXML
    private TextField textFieldPhone;

    @FXML
    private TextField textFieldAddress;

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
}
