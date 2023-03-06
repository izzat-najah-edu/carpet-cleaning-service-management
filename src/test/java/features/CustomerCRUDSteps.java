package features;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import javafx.scene.control.TableView;
import org.testfx.api.FxRobot;
import stu.najah.se.sql.entity.CustomerEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomerCRUDSteps extends FxRobot {

    private final TableView<CustomerEntity> tableCustomers
             = lookup("#t1tableCustomers").queryTableView();

    @And("customers panel is opened")
    public void customersPanelIsOpened() {
        clickOn("#tabCustomers");
    }

    @And("edit customer tab is opened")
    public void addCustomerTabIsOpened() {
        clickOn("#tabAll");
    }

    @Given("I enter a customer name, phone, address")
    public void iEnterACustomerNamePhoneAddress() {
        clickOn("#t1textFieldName");
        write("some name");
        clickOn("#t1textFieldPhone");
        write("0123456789");
        clickOn("#t1textFieldAddress");
        write("some address");
    }

    @And("the name is not empty")
    public void theNameIsNotEmpty() {
        // 'some name' is not an empty string
    }

    @And("I click add customer button")
    public void iClickAddCustomerButton() {
        clickOn("#t1buttonCreateCustomer");
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
        clickOn("#t1textFieldAddress");
        eraseText("some address".length());
        write("different address");
    }

    @And("I click update customer button")
    public void iClickUpdateCustomerButton() {
        clickOn("#t1buttonUpdateCustomer");
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
        clickOn("#t1buttonDeleteCustomer");
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
