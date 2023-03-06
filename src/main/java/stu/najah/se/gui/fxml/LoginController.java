package stu.najah.se.gui.fxml;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import stu.najah.se.Navigator;

public class LoginController {

    @FXML
    private TextField textFieldUsername;

    @FXML
    private PasswordField textFieldPassword;

    @FXML
    public void exit() {
        Navigator.exit();
    }

    @FXML
    public void login() {
        // try to log-in
        Navigator.login(
                textFieldUsername.getText(),
                textFieldPassword.getText()
        );
    }

    public void reset() {
        textFieldUsername.clear();
        textFieldPassword.clear();
    }
}
