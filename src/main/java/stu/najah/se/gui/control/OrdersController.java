package stu.najah.se.gui.control;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import stu.najah.se.data.dao.OrderDAO;
import stu.najah.se.data.dao.OrderProductDAO;
import stu.najah.se.data.entity.OrderProductEntity;
import stu.najah.se.data.entity.OrderViewEntity;
import stu.najah.se.gui.Controller;
import stu.najah.se.gui.Prompter;

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
    private TableView<OrderProductEntity> tableProducts;

    @FXML
    private TextField textFieldCustomerName;

    @FXML
    private TextField textFieldPrice;

    @FXML
    private TextField textFieldSpecialTreatment;

    private final OrderDAO orderDAO = new OrderDAO();
    private final OrderProductDAO productDAO = new OrderProductDAO();

    private OrderViewEntity selectedOrder = null;
    private OrderProductEntity selectedProduct = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXUtility.setUpTable(tableOrders);
        FXUtility.setUpTable(tableProducts);
        tableOrders.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    selectedOrder = newValue;
                    refreshToSelectedOrder();
                });
        tableProducts.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    selectedProduct = newValue;
                    refreshToSelectedProduct();
                });
    }

    @Override
    public void reset() {
        refreshOrdersTable();
    }

    private void refreshToSelectedOrder() {
        // finished() is in the order entity not the view entity
        if (selectedOrder != null) {
            var order = orderDAO.get(selectedOrder.getOrderId());
            labelOrderStatus.setText(
                    order.finished() ? "Finished" : "In Progress"
            );
        } else {
            labelOrderStatus.setText("-");
        }
        refreshProductsTable();
    }

    private void refreshToSelectedProduct() {
        if (selectedProduct != null) {
            textFieldSpecialTreatment.setText(selectedProduct.getSpecialTreatment());
            textFieldPrice.setText(selectedProduct.getPrice().toString());
            checkBoxFinished.setSelected(selectedProduct.getFinished() != 0);
        } else {
            clearSelectedProduct();
        }
    }

    @FXML
    private void refreshOrdersTable() {
        textFieldCustomerName.clear();
        tableOrders.setItems(orderDAO.getAllViews());
        tableOrders.getSelectionModel().clearSelection();
    }

    @FXML
    private void refreshProductsTable() {
        if (selectedProduct != null) {
            tableProducts.setItems(productDAO.getAll(selectedOrder.getOrderId()));
            tableProducts.getSelectionModel().clearSelection();
        } else {
            tableProducts.setItems(FXCollections.observableArrayList());
        }
    }

    @FXML
    private void clearSelectedProduct() {
        textFieldSpecialTreatment.clear();
        textFieldPrice.clear();
        checkBoxFinished.setSelected(false);
    }

    @FXML
    private void createProduct() {
        if (selectedOrder == null) {
            Prompter.warning("No order selected!");
            return;
        }
        var orderProduct = new OrderProductEntity();
        orderProduct.setOrderId(selectedProduct.getOrderId());
        orderProduct.setProductId(selectedProduct.getProductId());
        orderProduct.setSpecialTreatment(textFieldSpecialTreatment.getText());
        try {
            orderProduct.setPrice(Integer.parseInt(textFieldPrice.getText()));
        } catch (NumberFormatException e) {
            Prompter.error("Incorrect number format!");
        }
        orderProduct.setFinished((byte) (checkBoxFinished.isSelected() ? 1 : 0));
        if (productDAO.insert(orderProduct)) {
            refreshProductsTable();
        }
    }

    @FXML
    private void updateProduct() {
        if (selectedOrder == null) {
            Prompter.warning("No order selected!");
            return;
        }
        if (selectedProduct == null) {
            Prompter.warning("No product selected!");
            return;
        }
        selectedProduct.setSpecialTreatment(textFieldSpecialTreatment.getText());
        try {
            selectedProduct.setPrice(Integer.parseInt(textFieldPrice.getText()));
        } catch (NumberFormatException e) {
            Prompter.error("Incorrect number format!");
        }
        selectedProduct.setFinished((byte) (checkBoxFinished.isSelected() ? 1 : 0));
        if (productDAO.update(selectedProduct)) {
            refreshProductsTable();
        }
    }

    @FXML
    private void deleteProduct() {
        if (selectedOrder == null) {
            Prompter.warning("No order selected!");
            return;
        }
        if (selectedProduct == null) {
            Prompter.warning("No product selected!");
            return;
        }
        if (productDAO.delete(selectedProduct)) {
            refreshProductsTable();
        }
    }

    @FXML
    private void searchOrder() {
        tableOrders.setItems(orderDAO.getAllViews(textFieldCustomerName.getText()));
    }

    @FXML
    private void generateInvoice() {
        // TODO
    }
}
