package stu.najah.se.test.ui.features.aya;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import javafx.scene.Node;
import org.testfx.framework.junit5.ApplicationTest;
import stu.najah.se.ui.SceneManager;

import static org.junit.Assert.*;

public class LogoutFeature extends ApplicationTest {
    @Given("that the user is logged in")
    public void thatTheUserIsLoggedIn() {
        // Assuming user is already logged in
        assertFalse(SceneManager.getInstance().isLoggedIn());
        clickOn("#buttonLogin");
        iEnterCorrectUsernameAndCorrectPassword();
        iClickOnTheLoginButton();
        assertTrue(SceneManager.getInstance().isLoggedIn());
    }

    private void iClickOnTheLoginButton() {
    }

    private void iEnterCorrectUsernameAndCorrectPassword() {
    }

    @Given("that the user is not logged in")
    public void thatTheUserIsNotLoggedIn() {
        // Assuming user is already logged out
        assertFalse(SceneManager.getInstance().isLoggedIn());
    }

    @Given("that the user is logged in but the session has timed out")
    public void thatTheUserIsLoggedInButTheSessionHasTimedOut() {
        // Assuming user is already logged in
        assertFalse(SceneManager.getInstance().isLoggedIn());
        clickOn("#buttonLogin");
        iEnterCorrectUsernameAndCorrectPassword();
        iClickOnTheLoginButton();
        assertTrue(SceneManager.getInstance().isLoggedIn());

        // try {
        //  Thread.sleep(10000);
        // } catch (InterruptedException ignored) {
        //  }
    }

    @When("the user clicks on the logout button")
    public void theUserClicksOnTheLogoutButton() {
        clickOn("#buttonLogout");
    }

    @Then("the user session is terminated")
    public void theUserSessionIsTerminated() {
        assertFalse(SceneManager.getInstance().isLoggedIn());
    }

    @Then("the user is redirected to the login screen")
    public void theUserIsRedirectedToTheLoginScreen() {
        assertNull(lookup("#buttonLogout").query());
        assertNotNull(lookup("#buttonLogin").query());
    }

    @Then("an error message indicating the user is not logged in is prompted")
    public void anErrorMessageIndicatingTheUserIsNotLoggedInIsPrompted() {
        Node errorAlert = lookup(".alert").query();
        assertNotNull(errorAlert);
        assertTrue(errorAlert.isVisible());
        clickOn("OK");
    }

    @Then("an error message indicating the session has timed out is prompted")
    public void anErrorMessageIndicatingTheSessionHasTimedOutIsPrompted() {
        Node errorAlert = lookup(".alert").query();
        assertNotNull(errorAlert);
        assertTrue(errorAlert.isVisible());
        clickOn("OK");
    }

}
