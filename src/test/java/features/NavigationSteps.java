package features;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import javafx.application.Platform;
import org.testfx.api.FxRobot;
import stu.najah.se.Navigator;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class NavigationSteps extends FxRobot {

    @Given("main screen is opened")
    public void mainScreenIsOpened() {
        // fast login
        Platform.runLater(() -> {
            if(!Navigator.isLoggedIn()) {
                Navigator.login("admin", "admin");
            }
        });
    }

    @When("I click on logout button")
    public void iClickOnLogoutButton() {
        clickOn("#buttonLogout");
    }

    @Then("main screen switches to login screen")
    public void mainScreenSwitchesToLoginScreen() {
        assertFalse(Navigator.isLoggedIn());
    }
}
