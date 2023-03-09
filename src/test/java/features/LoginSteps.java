package features;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import javafx.application.Platform;
import org.testfx.api.FxRobot;
import stu.najah.se.Navigator;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps extends FxRobot {

    static final String container = "#loginPane ";

    @Given("login screen is opened")
    public void loginScreenIsOpened() {
        // logout or stay at login screen
        Platform.runLater(() -> Navigator.getSceneManager().logout());
    }

    @When("I enter correct username and correct password")
    public void iEnterCorrectUsernameAndCorrectPassword() {
        clickOn(container + "TextField.username");
        write("admin"); // test admin
        clickOn(container + "PasswordField.password");
        write("admin");
    }

    @And("I click on the login button")
    public void iClickOnTheLoginButton() {
        clickOn(container + "Button.login");
    }

    @Then("login screen switches to main screen")
    public void loginScreenSwitchesToMainScreen() {
        assertTrue(Navigator.getSceneManager().isLoggedIn());
    }

    @When("I enter wrong username or wrong password")
    public void iEnterWrongUsernameOrWrongPassword() {
        clickOn(container + "TextField.username");
        write("admin"); // test admin
        clickOn(container + "PasswordField.password");
        write("wrong");
    }

    @Then("error message indicating failed login is prompted")
    public void errorMessageIndicatingFailedLoginIsPrompted() {

    }
}
