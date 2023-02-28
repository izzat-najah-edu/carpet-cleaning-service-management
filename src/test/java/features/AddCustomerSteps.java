package features;

import io.cucumber.java.en.And;
import org.testfx.api.FxRobot;

public class AddCustomerSteps extends FxRobot {

    @And("customers panel is opened")
    public void customersPanelIsOpened() {
        clickOn("tabCustomers");
    }

    @And("add customer tab is opened")
    public void addCustomerTabIsOpened() {
        clickOn("tabAddCustomer");
    }

}
