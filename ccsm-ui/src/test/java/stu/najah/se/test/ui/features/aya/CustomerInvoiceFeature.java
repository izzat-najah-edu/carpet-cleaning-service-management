package stu.najah.se.test.ui.features.aya;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class CustomerInvoiceFeature {


    @Given("main screen is opened")
    public void mainScreenIsOpened() {
        // code to open the main screen
    }

    @Given("customer invoice tab is selected")
    public void customerInvoiceTabIsSelected() {
        // code to select the customer invoice tab
    }

    @Given("I select a customer from the customer box")
    public void selectACustomerFromCustomerBox() {
        // code to select a customer from the customer box
    }


    @Given("I click on the \"Create New Invoice\" button")
    public void clickOnCreateNewInvoiceButton() {
        // code to click on the "Create New Invoice" button
    }

    @And("I enter the necessary details for the new invoice")
    public void enterNewInvoiceDetails() {
        // code to enter new invoice details
    }

    @Then("a new invoice is created and added to the invoice list")
    public void verifyNewInvoiceCreated() {
        // code to verify that a new invoice is created and added to the invoice list
    }


    @Given("I select an invoice from the invoice list")
    public void selectAnInvoiceFromInvoiceList() {
        // code to select an invoice from the invoice list
    }

    @Given("I click on the \"Delete Invoice\" button")
    public void clickOnDeleteInvoiceButton() {
        // code to click on the "Delete Invoice" button
    }

    @Then("the selected invoice is deleted from the invoice list")
    public void verifySelectedInvoiceDeleted() {
        // code to verify that the selected invoice is deleted from the invoice list
    }


    @Given("I click on the \"Edit Invoice\" button")
    public void clickOnEditInvoiceButton() {
        // code to click on the "Edit Invoice" button
    }

    @And("I make changes to the invoice details")
    public void makeChangesToInvoiceDetails() {
        // code to make changes to the invoice details
    }

    @Then("the invoice details are updated in the invoice list")
    public void verifyInvoiceDetailsUpdated() {
        // code to verify that the invoice details are updated in the invoice list
    }


    @Given("I click on the \"View Invoice\" button")
    public void clickOnViewInvoiceButton() {
        // code to click on the "View Invoice" button
    }

    @Then("the details of the selected invoice are displayed on the screen")
    public void verifySelectedInvoiceDetailsDisplayed() {
        // code to verify that the details of the selected invoice are displayed on the screen
    }


    @Given("I click on the \"Send Invoice\" button")
    public void clickOnSendInvoiceButton() {
        // code to click on the "Send Invoice" button
    }

    @And("I enter the customer email address")
    public void enterCustomerEmailAddress() {
        // code to enter the customer email address
    }

    @Then("the invoice is sent to the customer's email address")
    public void verifyInvoiceSentToCustomer() {
        // code to verify that the invoice is sent to the customer's email
    }
}