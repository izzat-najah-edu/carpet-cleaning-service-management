package stu.najah.se.test.ui.features.aya;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class DiscountSteps {

    private final double DISCOUNT_RATE = 0.1; // 10% discount
    private final double THRESHOLD_AMOUNT = 400; // 400 NIS
    private double totalAmount;
    private double discountAmount;
    private double discountedPrice;
    private String message;

    @Given("a total amount of {double} NIS")
    public void a_total_amount_of_NIS(double amount) {
        this.totalAmount = amount;
    }

    @When("the customer checks for discount eligibility")
    public void the_customer_checks_for_discount_eligibility() {
        if (totalAmount > THRESHOLD_AMOUNT) {
            discountAmount = totalAmount * DISCOUNT_RATE;
            discountedPrice = totalAmount - discountAmount;
            message = String.format("Congratulations, you qualify for a %.0f%% discount!\n", DISCOUNT_RATE * 100);
            message += String.format("Discount amount: %.2f NIS\n", discountAmount);
            message += String.format("Discounted price: %.2f NIS\n", discountedPrice);
        } else {
            message = "Sorry, you do not qualify for a discount.";
        }
    }

    @Then("the system should display the discount message")
    public void the_system_should_display_the_discount_message() {
        Scanner scanner = new Scanner(message);
        assertEquals("Congratulations, you qualify for a %.0f%% discount!", scanner.nextLine());
        assertEquals("Discount amount: %.2f NIS", scanner.nextLine());
        assertEquals("Discounted price: %.2f NIS", scanner.nextLine());
        scanner.close();
    }

    @Then("the system should display the no-discount message")
    public void the_system_should_display_the_no_discount_message() {
        assertEquals("Sorry, you do not qualify for a discount.", message);
    }

}