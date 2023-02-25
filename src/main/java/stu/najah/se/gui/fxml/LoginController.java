package stu.najah.se.gui.fxml;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import stu.najah.se.Navigator;
import stu.najah.se.sql.entity.Admin;

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
        Navigator.login(new Admin(
                textFieldUsername.getText(),
                textFieldPassword.getText()
        ));
        // if it fails
        if(!Navigator.isLoggedIn()) {
            // todo: prompt fail...
            System.out.println("login failed");
        }
    }

    public TextField getTextFieldUsername() {
        return textFieldUsername;
    }

    public PasswordField getTextFieldPassword() {
        return textFieldPassword;
    }

    public void clear() {
        textFieldUsername.clear();
        textFieldPassword.clear();
    }
}
