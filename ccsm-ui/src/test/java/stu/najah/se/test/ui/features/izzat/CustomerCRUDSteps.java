package stu.najah.se.test.ui.features.izzat;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import javafx.scene.control.TableView;
import stu.najah.se.core.entity.CustomerEntity;

import java.util.Objects;

import static org.junit.Assert.*;

public class CustomerCRUDSteps extends ApplicationTestBase {

    private static final CustomerEntity CUSTOMER = new CustomerEntity(
            "some name", "0123456789", "some address", "some@email.com"
    );

    private final TableView<CustomerEntity> tableCustomers
            = lookup("#tableCustomers").queryTableView();

    @And("customer tab is opened")
    public void customerTabIsOpened() {
        clickOn("#tabCustomers");
    }

    @Given("I enter customer information")
    public void iEnterACustomerInformation() {
        type("#textFieldName", CUSTOMER.getName());
        type("#textFieldPhone", CUSTOMER.getPhone());
        type("#textFieldAddress", CUSTOMER.getAddress());
        type("#textFieldEmail", CUSTOMER.getEmail());
    }

    @And("the name is not empty")
    public void theNameIsNotEmpty() {
        assertFalse(CUSTOMER.getName().isEmpty());
    }

    @And("I click add customer button")
    public void iClickAddCustomerButton() {
        clickOn("#buttonCreateCustomer");
    }

    @Then("a new customer is added to the list")
    public void aNewCustomerIsAddedToTheList() {
        // find the new customer
        var matches = tableCustomers.getItems().stream().filter(customerEntity ->
                Objects.equals(CUSTOMER.getName(), customerEntity.getName()));
        // it exists & is unique
        assertEquals(1, matches.count());
    }

    @Given("I select a customer from the list")
    public void iSelectACustomerFromTheList() {
        for (int i = 0; i < tableCustomers.getItems().size(); i++) {
            if (Objects.equals(CUSTOMER.getName(), tableCustomers.getItems().get(i).getName())) {
                selectRow(i, tableCustomers);
                CUSTOMER.setId(tableCustomers.getItems().get(i).getId());
                break;
            }
        }
    }

    @And("I enter a different customer information")
    public void iEnterADifferentCustomerInformation() {
        type("#textFieldAddress", CUSTOMER.getAddress() + "-new");
        CUSTOMER.setAddress(CUSTOMER.getAddress() + "-new");
    }

    @And("I click update customer button")
    public void iClickUpdateCustomerButton() {
        clickOn("#buttonUpdateCustomer");
    }

    @Then("the selected customer is updated")
    public void theSelectedCustomerIsUpdated() {
        for (int i = 0; i < tableCustomers.getItems().size(); i++) {
            if (Objects.equals(CUSTOMER.getId(), tableCustomers.getItems().get(i).getId())) {
                assertEquals(
                        CUSTOMER.getAddress(),
                        tableCustomers.getItems().get(i).getAddress()
                );
                return;
            }
        }
        fail();
    }

    @And("I click delete customer button")
    public void iClickDeleteCustomerButton() {
        clickOn("#buttonDeleteCustomer");
    }

    @Then("the selected customer is deleted")
    public void theSelectedCustomerIsDeleted() {
        for (int i = 0; i < tableCustomers.getItems().size(); i++) {
            if (Objects.equals(CUSTOMER.getId(), tableCustomers.getItems().get(i).getId())) {
                fail();
            }
        }
    }
}
