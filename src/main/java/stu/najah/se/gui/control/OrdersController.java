package stu.najah.se.gui.control;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import stu.najah.se.gui.Controller;

import java.net.URL;
import java.util.ResourceBundle;

public class OrdersController
        implements Controller, Initializable {

    @FXML
    private Tab tab1;

    @FXML
    private Tab tab2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void reset() {

    }
}
