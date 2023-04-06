package stu.najah.se.gui.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import stu.najah.se.data.dao.OrderDAO;
import stu.najah.se.data.entity.OrderViewEntity;
import stu.najah.se.data.entity.ProductEntity;
import stu.najah.se.gui.Controller;

import java.net.URL;
import java.util.ResourceBundle;

public class OrdersController
        implements Controller, Initializable {

    @FXML
    private CheckBox checkBoxFinished;

    @FXML
    private Label labelOrderStatus;

    @FXML
    private TableView<OrderViewEntity> tableOrders;

    @FXML
    private TableView<ProductEntity> tableProducts;

    @FXML
    private TextField textFieldCustomerName;

    @FXML
    private TextField textFieldPrice;

    @FXML
    private TextField textFieldSpecialTreatment;

    private final OrderDAO orderDAO = new OrderDAO();

    private OrderViewEntity selectedOrder = null;
    private ProductEntity selectedProduct = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void reset() {

    }

    @FXML
    void clearSelectedProduct(ActionEvent event) {

    }

    @FXML
    void createProduct(ActionEvent event) {

    }

    @FXML
    void deleteProduct(ActionEvent event) {

    }

    @FXML
    void generateInvoice(ActionEvent event) {

    }

    @FXML
    void refreshOrdersTable(ActionEvent event) {

    }

    @FXML
    void refreshProductsTable(ActionEvent event) {

    }

    @FXML
    void updateProduct(ActionEvent event) {

    }
}
