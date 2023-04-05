package stu.najah.se.gui.fxml;

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
    public void exit() {
        Platform.exit();
    }

    @FXML
    public void login() {
        // send login request
        Authenticator.login(
                textFieldUsername.getText(),
                textFieldPassword.getText()
        );
    }
}
