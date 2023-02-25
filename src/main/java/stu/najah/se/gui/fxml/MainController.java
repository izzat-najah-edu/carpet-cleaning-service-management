package stu.najah.se.gui.fxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import stu.najah.se.Navigator;

public class MainController {

    @FXML
    private Button buttonCustomers;

    @FXML
    private Button buttonExit;

    @FXML
    private Button buttonLogout;

    @FXML
    private Button buttonOrders;

    @FXML
    void exit(ActionEvent event) {
        Navigator.exit();
    }

    @FXML
    void logout(ActionEvent event) {
        Navigator.logout();
    }

    @FXML
    void setCustomersPanel(ActionEvent event) {

    }

    @FXML
    void setOrdersPanel(ActionEvent event) {

    }

    public void clear() {

    }

}
