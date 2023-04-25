package stu.najah.se.ui.control;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import stu.najah.se.core.EntityListener;
import stu.najah.se.core.ServiceManager;
import stu.najah.se.core.entity.CustomerEntity;
import stu.najah.se.core.entity.OrderEntity;
import stu.najah.se.core.entity.OrderProductEntity;
import stu.najah.se.core.entity.ProductEntity;
import stu.najah.se.core.service.CustomerService;
import stu.najah.se.core.service.OrderProductService;
import stu.najah.se.core.service.OrderService;
import stu.najah.se.core.service.ProductService;
import stu.najah.se.ui.Controller;
import stu.najah.se.ui.Prompter;

import java.net.URL;
import java.util.ResourceBundle;

public class OrdersController
        implements Controller, Initializable, EntityListener<OrderProductEntity> {

    private CustomerService customerService;

    private OrderService orderService;

    private ProductService productService;

    private OrderProductService orderProductService;

    @FXML
    private TableView<OrderEntity> tableOrders;

    @FXML
    private TableView<OrderProductEntity> tableOrderProducts;

    @FXML
    private ComboBox<ProductEntity> comboBoxAvailableProducts;

    @FXML
    private ComboBox<CustomerEntity> comboBoxCustomer;

    @FXML
    private Label labelOrderStatus;

    @FXML
    private TextField textFieldPrice;

    @FXML
    private TextField textFieldSpecialTreatment;

    @FXML
    private CheckBox checkBoxFinished;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        customerService = ServiceManager.getCustomerService();
        orderService = ServiceManager.getOrderService();
        productService = ServiceManager.getProductService();
        orderProductService = ServiceManager.getOrderProductService();
        orderProductService.watchOrderProduct(this);
        FXUtility.setUpTable(tableOrders);
        FXUtility.setUpTable(tableOrderProducts);
        comboBoxCustomer.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    customerService.selectCustomer(newValue);
                    refreshOrdersTable();
                    refreshAvailableProducts();
                });
        tableOrders.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue == null) {
                        orderService.clearOrder();
                    } else {
                        orderService.selectOrder(newValue.getId());
                    }
                    refreshOrderProductsTable();
                });
        comboBoxAvailableProducts.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue == null) {
                        productService.clearProduct();
                    } else {
                        productService.selectProduct(newValue.getId());
                    }
                });
        tableOrderProducts.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue == null) {
                        orderProductService.clearOrderProduct();
                    } else {
                        orderProductService.selectOrderProduct(newValue.getOrderId(), newValue.getProductId());
                    }
                });
    }

    @Override
    public void onEntityChanged(OrderProductEntity newEntity) {
        textFieldSpecialTreatment.setText(newEntity.getSpecialTreatment());
        textFieldPrice.setText(String.valueOf(newEntity.getPrice()));
        checkBoxFinished.setSelected(newEntity.getFinished() != 0);
    }

    @Override
    public void onEntityCleared() {
        textFieldSpecialTreatment.clear();
        textFieldPrice.clear();
        checkBoxFinished.setSelected(false);
    }

    @Override
    public void reset() {
        refreshComboBoxCustomers();
        refreshOrdersTable();
    }

    private void refreshComboBoxCustomers() {
        comboBoxCustomer.setItems(FXCollections.observableArrayList(customerService.getAllCustomers()));
        refreshAvailableProducts();
    }

    private void refreshAvailableProducts() {
        try {
            var list = productService.getAllCustomerAvailableProducts();
            comboBoxAvailableProducts.setItems(FXCollections.observableArrayList(list));
        } catch (IllegalStateException e) {
            comboBoxAvailableProducts.setItems(FXCollections.observableArrayList());
        }
    }

    private void refreshLabelOrderStatus() {
        try {
            labelOrderStatus.setText(orderService.isOrderFinished() ? "Finished" : "Waiting");
        } catch (IllegalStateException e) {
            labelOrderStatus.setText(null);
        }
    }

    @FXML
    private void refreshOrdersTable() {
        try {
            var list = orderService.getAllCustomerOrders();
            tableOrders.setItems(FXCollections.observableArrayList(list));
        } catch (IllegalStateException e) {
            tableOrders.setItems(FXCollections.observableArrayList());
        }
    }

    @FXML
    private void refreshOrderProductsTable() {
        try {
            var list = orderProductService.getAllOrderProducts();
            tableOrderProducts.setItems(FXCollections.observableArrayList(list));
        } catch (IllegalStateException e) {
            tableOrderProducts.setItems(FXCollections.observableArrayList());
        } finally {
            refreshLabelOrderStatus();
        }
    }

    @FXML
    private void clearSelectedOrderProduct() {
        textFieldSpecialTreatment.clear();
        textFieldPrice.clear();
        checkBoxFinished.setSelected(false);
    }

    @FXML
    private void updateOrderProduct() {
        try {
            orderProductService.updateOrderProduct(createProductFromTextFields());
        } catch (NumberFormatException | IllegalStateException e) {
            Prompter.getInstance().error(e);
        } finally {
            refreshOrderProductsTable();
        }
    }

    @FXML
    private void deleteOrderProduct() {
        try {
            orderProductService.deleteOrderProduct();
        } catch (IllegalStateException e) {
            Prompter.getInstance().error(e);
        } finally {
            refreshOrderProductsTable();
            refreshAvailableProducts();
        }
    }

    @FXML
    private void createOrderProduct() {
        try {
            orderProductService.createAndSelectOrderProduct(createProductFromTextFields());
        } catch (NumberFormatException | IllegalStateException e) {
            Prompter.getInstance().error(e);
        } finally {
            refreshOrderProductsTable();
            refreshAvailableProducts();
        }
    }

    @FXML
    private void createOrder() {
        try {
            orderService.createAndSelectOrder();
        } catch (IllegalStateException e) {
            Prompter.getInstance().error(e);
        } finally {
            refreshOrdersTable();
        }
    }

    @FXML
    private void deleteOrder() {
        try {
            orderService.deleteOrder();
        } catch (IllegalStateException e) {
            Prompter.getInstance().error(e);
        } finally {
            refreshOrdersTable();
        }
    }

    @FXML
    private void generateInvoice() {
        // TODO
    }

    private OrderProductEntity createProductFromTextFields() throws NumberFormatException {
        var orderProduct = new OrderProductEntity();
        orderProduct.setSpecialTreatment(textFieldSpecialTreatment.getText());
        orderProduct.setPrice(Integer.parseInt(textFieldPrice.getText()));
        orderProduct.setFinished((byte) (checkBoxFinished.isSelected() ? 1 : 0));
        return orderProduct;
    }
}
