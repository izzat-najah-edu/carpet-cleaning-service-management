package stu.najah.se.test.ui.features;

import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;
import stu.najah.se.ui.SceneManager;

import static org.junit.Assert.*;

public class LoginSteps extends ApplicationTest {

    private FxRobot robot;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource("fxml/login.fxml"));
        AnchorPane root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @BeforeStep
    public void prepare() {
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Given("login screen is opened")
    public void loginScreenIsOpened() {
        robot = new FxRobot();
    }

    @When("I enter correct username and correct password")
    public void iEnterCorrectUsernameAndCorrectPassword() {
        robot.clickOn("#textFieldUsername");
        robot.write("admin");
        robot.clickOn("#textFieldPassword");
        robot.write("admin");
    }

    @When("I enter wrong username or wrong password")
    public void iEnterWrongUsernameOrWrongPassword() {
        robot.clickOn("#textFieldUsername");
        robot.write("wrong_username");
        robot.clickOn("#textFieldPassword");
        robot.write("wrong_password");
    }

    @When("I click on the login button")
    public void iClickOnTheLoginButton() {
        robot.clickOn("#login");
    }

    @Then("login screen switches to main screen")
    public void loginScreenSwitchesToMainScreen() {
        assertTrue(SceneManager.getInstance().isLoggedIn());
    }

    @Then("error message indicating failed login is prompted")
    public void errorMessageIndicatingFailedLoginIsPrompted() {
        Label errorLabel = robot.lookup("#errorLabel").queryAs(Label.class);
        assertNotNull(errorLabel);
        assertFalse(errorLabel.getText().isEmpty());
    }
}
