package stu.najah.se.test.ui.features;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import javafx.application.Platform;
import org.testfx.api.FxRobot;
import stu.najah.se.ui.SceneManager;

import static org.junit.Assert.assertFalse;

public class NavigationSteps extends FxRobot {

    static final String wrapper = "#navbarPane ";

    @Given("main screen is opened")
    public void mainScreenIsOpened() {
        // fast login
        Platform.runLater(() -> SceneManager.getInstance().login());
    }

    @When("I click on logout button")
    public void iClickOnLogoutButton() {
        clickOn(wrapper + "Button.logout");
    }

    @Then("main screen switches to login screen")
    public void mainScreenSwitchesToLoginScreen() {
        assertFalse(SceneManager.getInstance().isLoggedIn());
    }

}
