package stu.najah.se.test.ui.features.izzat;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import javafx.application.Platform;
import org.testfx.framework.junit5.ApplicationTest;
import stu.najah.se.core.ServiceManager;
import stu.najah.se.ui.SceneManager;

import static org.junit.Assert.assertFalse;

public class NavigationSteps extends ApplicationTest {

    @Given("main screen is opened")
    public void mainScreenIsOpened() {
        Platform.runLater(() ->
                ServiceManager.getAdminService().authenticate("admin", "admin"));
    }

    @When("I click on logout button")
    public void iClickOnLogoutButton() {
        clickOn("#buttonLogout");
    }

    @Then("main screen switches to login screen")
    public void mainScreenSwitchesToLoginScreen() {
        assertFalse(SceneManager.getInstance().isLoggedIn());
    }

}
