package stu.najah.se.test.ui.features;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testfx.api.FxRobot;

public class NavigationSteps extends FxRobot {

    static final String wrapper = "#navbarPane ";

    @Given("main screen is opened")
    public void mainScreenIsOpened() {
        // fast login
        //Platform.runLater(() -> Navigator.getSceneManager().login(
        //        "admin", "admin"));
    }

    @When("I click on logout button")
    public void iClickOnLogoutButton() {
        clickOn(wrapper + "Button.logout");
    }

    @Then("main screen switches to login screen")
    public void mainScreenSwitchesToLoginScreen() {
        //assertFalse(Navigator.getSceneManager().isLoggedIn());
    }

}
