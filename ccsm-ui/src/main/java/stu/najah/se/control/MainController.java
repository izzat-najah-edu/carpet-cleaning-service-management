package stu.najah.se.control;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import stu.najah.se.Controller;
import stu.najah.se.scene.Authenticator;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController
        implements Controller, Initializable {

    @FXML
    private BorderPane mainPane;

    @FXML
    private AnchorPane navbarPane;

    @FXML
    private Tab tabCustomers;

    @FXML
    private Tab tabProducts;

    @FXML
    private Tab tabOrders;

    @FXML
    private CustomersController paneCustomersController;

    @FXML
    private ProductsController paneProductsController;

    @FXML
    private OrdersController paneOrdersController;

    @FXML
    private AnchorPane paneCustomers;

    @FXML
    private AnchorPane paneProducts;

    @FXML
    private AnchorPane paneOrders;

    /**
     * Temporary values for positioning
     */
    private double x, y;

    private void initializeMouseEvents() {
        navbarPane.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        navbarPane.setOnMouseDragged(event -> {
            var stage = (Stage) navbarPane.getScene().getWindow();
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeMouseEvents();
    }

    @FXML
    @Override
    public void reset() {
        paneCustomersController.reset();
        paneProductsController.reset();
        paneOrdersController.reset();
    }

    @FXML
    private void exit() {
        Platform.exit();
    }

    @FXML
    private void logout() {
        Authenticator.logout();
    }
}
