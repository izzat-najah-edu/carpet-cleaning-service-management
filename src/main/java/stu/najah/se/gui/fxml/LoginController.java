package stu.najah.se.gui.fxml;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import stu.najah.se.Navigator;

public class LoginController
        implements Controller {

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
        Navigator.exit();
    }

    @FXML
    public void login() {
        // send login request
        Navigator.getSceneManager().login(
                textFieldUsername.getText(),
                textFieldPassword.getText()
        );
    }
}
