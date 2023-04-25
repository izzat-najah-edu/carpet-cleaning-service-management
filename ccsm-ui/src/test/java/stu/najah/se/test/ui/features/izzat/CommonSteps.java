package stu.najah.se.test.ui.features.izzat;

import io.cucumber.java.en.Given;
import javafx.application.Platform;
import stu.najah.se.core.ServiceManager;

public class CommonSteps {

    @Given("main screen is opened")
    public void mainScreenIsOpened() {
        Platform.runLater(() ->
                ServiceManager.getAdminService().authenticate("admin", "admin"));
    }

}
