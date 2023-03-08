package stu.najah.se.gui.fxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import stu.najah.se.Navigator;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController
    implements Controller, Initializable {
    @FXML
    private AnchorPane navigationBar;

    @FXML
    private Tab tabCustomers;

    @FXML
    private Tab tabOrders;

    @FXML
    private CustomersController paneCustomersController;

    @FXML
    private OrdersController paneOrdersController;

    @FXML
    private AnchorPane paneCustomers;

    @FXML
    private AnchorPane paneOrders;

    /**
     * Temporary values for positioning
     */
    private double x, y;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        var stage = Navigator.getSceneManager().getStage();
        navigationBar.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        navigationBar.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
        });
    }

    @FXML
    @Override
    public void reset() {
        paneCustomersController.reset();
        paneOrdersController.reset();
    }

    @FXML
    public void exit(ActionEvent event) {
        Navigator.exit();
    }

    @FXML
    public void logout(ActionEvent event) {
        Navigator.getSceneManager().logout();
    }
}
