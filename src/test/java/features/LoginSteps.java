package features;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {

    @Given("the login screen is displayed")
    public void theLoginScreenIsDisplayed() {
        // to be implemented
        System.out.println("*** imagine the login screen is displayed :)");
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
