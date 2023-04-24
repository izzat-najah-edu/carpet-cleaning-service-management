package stu.najah.se.test.ui.features.izzat;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import javafx.scene.control.TableView;
import org.testfx.api.FxRobot;
import stu.najah.se.core.entity.CustomerEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CustomerCRUDSteps extends FxRobot {

    private final TableView<CustomerEntity> tableCustomers
            = lookup("#t1tableCustomers").queryTableView();

    @And("customers panel is opened")
    public void customersPanelIsOpened() {
        clickOn("#tabAll");
    }

    @And("edit customer tab is opened")
    public void addCustomerTabIsOpened() {
        clickOn("#tabAll");
    }

    @Given("I enter a customer name, phone, address")
    public void iEnterACustomerNamePhoneAddress() {
        clickOn("#tabAll>TextField.name");
        write("some name");
        clickOn("#tabAll>TextField.phone");
        write("0123456789");
        clickOn("#tabAll>TextField.address");
        write("some address");
    }

    @And("the name is not empty")
    public void theNameIsNotEmpty() {
        // 'some name' is not an empty string
    }

    @And("I click add customer button")
    public void iClickAddCustomerButton() {
        clickOn("#tabAll>Button.create");
    }

    @Then("a new customer is added to the list")
    public void aNewCustomerIsAddedToTheList() {
        int rowIndex = -1;
        for (int i = 0; i < tableCustomers.getItems().size(); i++) {
            if (tableCustomers.getItems().get(i).getName().equals("some name")) {
                rowIndex = i;
                break;
            }
        }
        assertTrue(rowIndex != -1);
    }

    @Given("I select a customer from the list")
    public void iSelectACustomerFromTheList() {
        for (int i = 0; i < tableCustomers.getItems().size(); i++) {
            if (tableCustomers.getItems().get(i).getName().equals("some name")) {
                tableCustomers.getSelectionModel().clearAndSelect(i);
                break;
            }
        }
    }

    @And("I enter a different customer information")
    public void iEnterADifferentCustomerInformation() {
        clickOn("#tabAll>TextField.address");
        eraseText("some address".length());
        write("different address");
    }

    @And("I click update customer button")
    public void iClickUpdateCustomerButton() {
        clickOn("#tabAll>Button.update");
    }

    @Then("the selected customer is updated")
    public void theSelectedCustomerIsUpdated() {
        for (int i = 0; i < tableCustomers.getItems().size(); i++) {
            if (tableCustomers.getItems().get(i).getName().equals("some name")) {
                assertEquals("0123456789", tableCustomers.getItems().get(i).getPhone());
                assertEquals("different address", tableCustomers.getItems().get(i).getAddress());
                break;
            }
        }
    }

    @And("I click delete customer button")
    public void iClickDeleteCustomerButton() {
        clickOn("#tabAll>Button.delete");
    }

    @Then("the selected customer is deleted")
    public void theSelectedCustomerIsDeleted() {
        int rowIndex = -1;
        for (int i = 0; i < tableCustomers.getItems().size(); i++) {
            if (tableCustomers.getItems().get(i).getName().equals("some name")) {
                rowIndex = i;
                break;
            }
        }
        assertEquals(-1, rowIndex);
    }
}
