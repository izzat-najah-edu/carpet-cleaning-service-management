package features;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import javafx.application.Platform;
import stu.najah.se.Navigator;

/**
 * Step Definitions Class.
 * For steps that are responsible for changing the switching screens.
 * E.g. login, exit...
 */
public class SwitchingSteps {

    @BeforeAll
    public static void launch() {
        // Launch the JavaFX application
        Navigator.main(new String[]{});
    }

    @AfterAll
    public static void exit() {
        Platform.exit();
    }

    @Given("the login screen is opened")
    public void theLoginScreenIsOpened() {

    }

    @Then("the login screen closes")
    public void theLoginScreenCloses() {
        
    }

    @And("the main screen opens")
    public void theMainScreenOpens() {
        
    }

    @Given("the main screen is opened")
    public void theMainScreenIsOpened() {
        
    }

    @Then("the customers panel opens")
    public void theCustomersPanelOpens() {
        
    }

    @Then("the orders panel opens")
    public void theOrdersPanelOpens() {
        
    }

    @Then("the main screen closes")
    public void theMainScreenCloses() {
    }
}
