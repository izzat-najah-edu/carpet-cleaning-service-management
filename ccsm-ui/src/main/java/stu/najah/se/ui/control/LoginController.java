package stu.najah.se.ui.control;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import stu.najah.se.core.ServiceManager;
import stu.najah.se.ui.Controller;

public class LoginController
        implements Controller {

    @FXML
    private AnchorPane loginPane;

    @FXML
    private TextField textFieldUsername;

    @FXML
    private PasswordField textFieldPassword;

    @Override
    public void reset() {
        textFieldUsername.clear();
        textFieldPassword.clear();
    }

    @FXML
    private void exit() {
        Platform.exit();
    }

    @FXML
    private void login() {
        // send login request
        ServiceManager.getAdminService().authenticate(
                textFieldUsername.getText(),
                textFieldPassword.getText()
        );
    }
}
