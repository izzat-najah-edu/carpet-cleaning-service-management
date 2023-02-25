package features;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import javafx.application.Platform;
import org.testfx.api.FxRobot;
import org.testfx.util.WaitForAsyncUtils;
import stu.najah.se.Navigator;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps extends FxRobot {

    @BeforeAll
    public static void launch() {
        // start the application
        Navigator.main(new String[]{});
    }

    @AfterAll
    public static void exit() {
        // close the application
        Navigator.exit();
    }

    @BeforeStep
    public static void waitForEvents() {
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Given("login screen is opened")
    public void loginScreenIsOpened() {
        // logout or stay at login screen
        Platform.runLater(() -> {
            if(Navigator.isLoggedIn()) {
                Navigator.logout();
            }
        });
    }

    @When("I enter correct username and correct password")
    public void iEnterCorrectUsernameAndCorrectPassword() {
        clickOn("#textFieldUsername");
        write("admin"); // test admin
        clickOn("#textFieldPassword");
        write("admin");
    }

    @And("I click on the login button")
    public void iClickOnTheLoginButton() {
        clickOn("#buttonLogin");
    }

    @Then("login screen switches to main screen")
    public void loginScreenSwitchesToMainScreen() {
        assertTrue(Navigator.isLoggedIn());
    }

    @When("I enter wrong username or wrong password")
    public void iEnterWrongUsernameOrWrongPassword() {
        clickOn("#textFieldUsername");
        write("admin"); // test admin
        clickOn("#textFieldPassword");
        write("wrong password");
    }

    @Then("error message indicating failed login is prompted")
    public void errorMessageIndicatingFailedLoginIsPrompted() {
        //lookup("#prompt").query();
    }
}
