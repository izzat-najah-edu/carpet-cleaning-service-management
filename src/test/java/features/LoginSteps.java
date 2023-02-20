package features;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.testfx.util.WaitForAsyncUtils;
import stu.najah.se.gui.Launcher;

import java.io.IOException;

public class LoginSteps {

    @BeforeAll
    public static void setup() {
        // Launch the JavaFX application
        Platform.startup(() -> {
            try {
                new Launcher().start(new Stage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @AfterAll
    public static void cleanup() {
        Platform.exit();
    }

    @Given("the login screen is displayed")
    public void theLoginScreenIsDisplayed() {
        // launch the application
        WaitForAsyncUtils.waitForFxEvents();
        for (int i = 0; i < 1000000; i++) {
            System.out.println(i);
        }
    }

    @When("I enter the correct username and the correct password")
    public void iEnterTheCorrectUsernameAndTheCorrectPassword() {
        // to be implemented
        System.out.println("*** you entered the correct login information :)");
    }

    @Then("the login screen closes")
    public void theLoginScreenCloses() {
        // to be implemented
        System.out.println("*** imagine login screen closed :)");
    }

    @And("the main screen opens")
    public void theMainScreenOpens() {
        // to be implemented
        System.out.println("*** imagine main screen opened :)");
    }

    @When("I enter a wrong username or a wrong password")
    public void iEnterAWrongUsernameOrAWrongPassword() {
        // to be implemented
        System.out.println("*** you entered wrong username or password :(");
    }

    @Then("an error message indicating a failed login is displayed")
    public void anErrorMessageIndicatingAFailedLoginIsDisplayed() {
        // to be implemented
        System.out.println("*** login failed :(");
    }

    @When("I click the exit button")
    public void iPressTheExitButton() {
    }
}
