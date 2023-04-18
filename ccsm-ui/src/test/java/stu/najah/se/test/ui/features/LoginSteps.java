package stu.najah.se.test.ui.features;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testfx.api.FxRobot;

public class LoginSteps extends FxRobot {

    static final String container = "#loginPane ";

    @Given("login screen is opened")
    public void loginScreenIsOpened() {
        // logout or stay at login screen
        //Platform.runLater(Authenticator::logout);
    }

    @When("I enter correct username and correct password")
    public void iEnterCorrectUsernameAndCorrectPassword() {
        clickOn(container + "TextField.username");
        write("admin"); // test admin
        clickOn(container + "PasswordField.password");
        write("admin");
    }

    @And("I click on the login button")
    public void iClickOnTheLoginButton() {
        clickOn(container + "Button.login");
    }

    @Then("login screen switches to main screen")
    public void loginScreenSwitchesToMainScreen() {
        //assertTrue(Authenticator.isLoggedIn());
    }

    @When("I enter wrong username or wrong password")
    public void iEnterWrongUsernameOrWrongPassword() {
        clickOn(container + "TextField.username");
        write("admin"); // test admin
        clickOn(container + "PasswordField.password");
        write("wrong");
    }

    @Then("error message indicating failed login is prompted")
    public void errorMessageIndicatingFailedLoginIsPrompted() {

    }
}
