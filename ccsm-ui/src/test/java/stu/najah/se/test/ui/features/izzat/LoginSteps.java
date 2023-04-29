package stu.najah.se.test.ui.features.izzat;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testfx.api.FxRobotException;
import stu.najah.se.ui.SceneManager;

import static org.junit.Assert.assertTrue;

public class LoginSteps extends ApplicationTestBase {

    @Given("login screen is opened")
    public void loginScreenIsOpened() {
        try {
            clickOn("#buttonLogout");
        } catch (FxRobotException ignored) {
            // already logged out
        }
    }

    @When("I enter correct username and correct password")
    public void iEnterCorrectUsernameAndCorrectPassword() {
        clickOn("#textFieldUsername");
        write("admin");
        clickOn("#textFieldPassword");
        write("admin");
    }

    @When("I enter wrong username or wrong password")
    public void iEnterWrongUsernameOrWrongPassword() {
        clickOn("#textFieldUsername");
        write("wrong_username");
        clickOn("#textFieldPassword");
        write("wrong_password");
    }

    @When("I click on the login button")
    public void iClickOnTheLoginButton() {
        clickOn("#buttonLogin");
    }

    @Then("login screen switches to main screen")
    public void loginScreenSwitchesToMainScreen() {
        assertTrue(SceneManager.getInstance().isLoggedIn());
    }

    @Then("error message indicating failed login is prompted")
    public void errorMessageIndicatingFailedLoginIsPrompted() {
        clickOkToAlert();
    }
}
