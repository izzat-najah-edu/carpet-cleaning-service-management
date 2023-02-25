package features;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import javafx.application.Platform;
import org.testfx.api.FxRobot;
import stu.najah.se.Navigator;
import stu.najah.se.sql.entity.Admin;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class MainSteps extends FxRobot {

    @Given("main screen is opened")
    public void mainScreenIsOpened() {
        // fast login
        Platform.runLater(() -> {
            if(!Navigator.isLoggedIn()) {
                Navigator.login(new Admin("admin", "admin"));
            }
        });
    }

    @When("I click on customers panel")
    public void iClickOnCustomersPanel() {
        clickOn("#buttonCustomers");
    }

    @Then("customers panel opens")
    public void customersPanelOpens() {

    }

    @When("I click on orders panel")
    public void iClickOnOrdersPanel() {
        clickOn("#buttonOrders");
    }

    @Then("orders panel opens")
    public void ordersPanelOpens() {

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
