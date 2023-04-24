package stu.najah.se.test.ui.features.izzat;

import io.cucumber.java.AfterStep;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import javafx.scene.Node;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;
import stu.najah.se.ui.SceneManager;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class LoginSteps extends ApplicationTest {

    @Given("login screen is opened")
    public void loginScreenIsOpened() throws Exception {
        ApplicationTest.launch(SceneManager.class);
    }

    @AfterStep
    public void waitForEffects() {
        WaitForAsyncUtils.waitForFxEvents();
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
        Node errorAlert = lookup(".alert").query();
        assertNotNull(errorAlert);
        assertTrue(errorAlert.isVisible());
        clickOn("OK");
    }
}
