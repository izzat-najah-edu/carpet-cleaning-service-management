package stu.najah.se.gui.control;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import stu.najah.se.data.dao.CustomerDAO;
import stu.najah.se.data.dao.OrderDAO;
import stu.najah.se.data.dao.OrderProductDAO;
import stu.najah.se.data.dao.ProductDAO;
import stu.najah.se.data.entity.*;
import stu.najah.se.gui.Controller;
import stu.najah.se.gui.Prompter;

import java.net.URL;
import java.util.ResourceBundle;

public class OrdersController
        implements Controller, Initializable {

    @FXML
    private TableView<OrderViewEntity> tableOrders;

    @FXML
    private TableView<OrderProductEntity> tableOrderProducts;

    @FXML
    private ComboBox<ProductEntity> comboBoxAvailableProducts;

    @FXML
    private Label labelSelectedCustomer;

    @FXML
    private Label labelOrderStatus;

    @FXML
    private TextField textFieldCustomerName;

    @FXML
    private TextField textFieldPrice;

    @FXML
    private TextField textFieldSpecialTreatment;

    @FXML
    private CheckBox checkBoxFinished;

    private final OrderDAO orderDAO = new OrderDAO();
    private final CustomerDAO customerDAO = new CustomerDAO();
    private final OrderProductDAO orderProductDAO = new OrderProductDAO();
    private final ProductDAO productDAO = new ProductDAO();

    private OrderViewEntity selectedOrderView = null;
    private OrderProductEntity selectedOrderProduct = null;
    private ProductEntity selectedProduct = null;

    private OrderEntity selectedOrder() {
        return orderDAO.get(selectedOrderView.getOrderId());
    }

    private CustomerEntity selectedCustomer() {
        var order = orderDAO.get(selectedOrderView.getOrderId());
        return customerDAO.get(order.getCustomerId());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Utility.setUpTable(tableOrders);
        Utility.setUpTable(tableOrderProducts);
        tableOrders.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    selectedOrderView = newValue;
                    refreshSelectedOrder();
                    refreshSelectedCustomer();
                    refreshProductsThenFields();
                });
        tableOrderProducts.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    productToTextFields(selectedOrderProduct = newValue);
                });
        comboBoxAvailableProducts.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    selectedProduct = newValue;
                });
    }

    @Override
    public void reset() {
        refreshOrdersThenProductsThenFields();
    }

    @FXML
    private void refreshOrdersThenProductsThenFields() {
        clearSearchFields();
        tableOrders.setItems(orderDAO.getAllViews());
        tableOrders.getSelectionModel().clearSelection();
    }

    @FXML
    private void refreshProductsThenFields() {
        if (selectedOrderProduct != null) {
            tableOrderProducts.setItems(orderProductDAO.getAll(selectedOrderView.getOrderId()));
        } else {
            tableOrderProducts.setItems(FXCollections.observableArrayList());
        }
        tableOrderProducts.getSelectionModel().clearSelection();
    }

    @FXML
    private void clearSelectedOrderProduct() {
        textFieldSpecialTreatment.clear();
        textFieldPrice.clear();
        checkBoxFinished.setSelected(false);
    }

    @FXML
    private void updateOrderProduct() {
        if (selectedOrderView == null) {
            Prompter.warning(Utility.NO_SELECTED_ORDER_MESSAGE);
            return;
        }
        if (selectedOrderProduct == null) {
            Prompter.warning(Utility.NO_SELECTED_PRODUCT_MESSAGE);
            return;
        }
        try {
            textFieldsToProduct(selectedOrderProduct);
            if (orderProductDAO.update(selectedOrderProduct)) {
                refreshProductsThenFields();
            }
        } catch (NumberFormatException e) {
            Prompter.error(Utility.NUMBER_FORMAT_ERROR_MESSAGE);
        }
    }

    @FXML
    private void deleteOrderProduct() {
        if (selectedOrderView == null) {
            Prompter.warning(Utility.NO_SELECTED_ORDER_MESSAGE);
            return;
        }
        if (selectedOrderProduct == null) {
            Prompter.warning(Utility.NO_SELECTED_PRODUCT_MESSAGE);
            return;
        }
        if (orderProductDAO.delete(selectedOrderProduct)) {
            refreshProductsThenFields();
        }
    }

    @FXML
    private void createOrderProduct() {
        if (selectedOrderView == null) {
            Prompter.warning(Utility.NO_SELECTED_ORDER_MESSAGE);
            return;
        }
        if (selectedProduct == null) {
            Prompter.warning(Utility.NO_SELECTED_PRODUCT_MESSAGE);
            return;
        }
        var orderProduct = new OrderProductEntity();
        orderProduct.setOrderId(selectedOrderView.getOrderId());
        orderProduct.setProductId(selectedProduct.getId());
        textFieldsToProduct(orderProduct);
        if (orderProductDAO.insert(orderProduct)) {
            refreshProductsThenFields();
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

    private void refreshSelectedOrder() {
        // finished() is in the order not the view
        if (selectedOrderView != null) {
            var order = selectedOrder();
            labelOrderStatus.setText(order.finished() ? "Finished" : "Waiting");
        } else {
            labelOrderStatus.setText(null);
        }
    }

    private void refreshSelectedCustomer() {
        if (selectedOrderView != null) {
            var customer = selectedCustomer();
            labelSelectedCustomer.setText(customer.getName());
            comboBoxAvailableProducts.setItems(productDAO.getAllAvailable(customer.getId()));
        } else {
            labelSelectedCustomer.setText(null);
        }
    }

    private void clearSearchFields() {
        textFieldCustomerName.clear();
    }

    private void clearTextFields() {
        textFieldSpecialTreatment.clear();
        textFieldPrice.clear();
        checkBoxFinished.setSelected(false);
    }

    private void productToTextFields(OrderProductEntity orderProduct) {
        if (orderProduct != null) {
            textFieldSpecialTreatment.setText(orderProduct.getSpecialTreatment());
            textFieldPrice.setText(String.valueOf(orderProduct.getPrice()));
            checkBoxFinished.setSelected(orderProduct.getFinished() != 0);
        } else {
            clearTextFields();
        }
    }

    private void textFieldsToProduct(OrderProductEntity orderProduct)
            throws NumberFormatException {
        orderProduct.setSpecialTreatment(textFieldSpecialTreatment.getText());
        orderProduct.setPrice(Integer.parseInt(textFieldPrice.getText()));
        orderProduct.setFinished((byte) (checkBoxFinished.isSelected() ? 1 : 0));
    }
}
