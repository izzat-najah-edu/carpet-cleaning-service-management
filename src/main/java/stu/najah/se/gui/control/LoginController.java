package stu.najah.se.gui.control;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import stu.najah.se.gui.Controller;
import stu.najah.se.gui.scene.Authenticator;

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
        Authenticator.login(
                textFieldUsername.getText(),
                textFieldPassword.getText()
        );
    }
}
