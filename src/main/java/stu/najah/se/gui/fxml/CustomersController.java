package stu.najah.se.gui.fxml;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import stu.najah.se.dao.CustomerDAO;
import stu.najah.se.sql.entity.CustomerEntity;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomersController
    implements Initializable {

    @FXML
    private Tab tabAddCustomer;

    @FXML
    private Tab tab2;

    @FXML
    private TableView<CustomerEntity> tableCustomers;

    private final CustomerDAO customerDAO = new CustomerDAO();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TableColumn<?, ?> column = tableCustomers.getColumns().get(0);
        column.setCellValueFactory(new PropertyValueFactory<>("id"));
        column = tableCustomers.getColumns().get(1);
        column.setCellValueFactory(new PropertyValueFactory<>("name"));
        column = tableCustomers.getColumns().get(2);
        column.setCellValueFactory(new PropertyValueFactory<>("phone"));
        column = tableCustomers.getColumns().get(3);
        column.setCellValueFactory(new PropertyValueFactory<>("address"));
        refresh();
    }

    @FXML
    public void refresh() {
        tableCustomers.setItems(customerDAO.getAll());
    }
}
