Feature: Product Review
  As a customer, I want to write a review for a product I have purchased, so that other potential buyers can benefit from my experience.

  Background: User purchased a product
    Given that the user has purchased a product
    And the user is logged in

  Scenario: Successful Product Review
    Given that the user is on the product page
    When the user clicks on the write a review button
    And the user fills out the review form
    And submits the form
    Then the review is added to the product page
    And a success message is displayed

  Scenario: Review without Login
    Given that the user is not logged in
    When the user clicks on the write a review button
    Then the user is redirected to the login page
    And a message prompts the user to log in first

  Scenario: Incomplete Review Form
    Given that the user is on the review form page
    When the user submits the form with missing required fields
    Then an error message is displayed asking the user to fill out all required fields

  Scenario: Maximum Review Length
    Given that the user is on the review form page
    When the user fills out the review form with more than the maximum allowed characters
    Then an error message is displayed indicating the maximum review length has been exceeded

  Scenario: Editing a Review
    Given that the user has already written a review for a product
    When the user clicks on the edit review button
    And the user edits the review form
    And submits the form
    Then the review is updated on the product page
    And a success message is displayed

  Scenario: Deleting a Review
    Given that the user has already written a review for a product
    When the user clicks on the delete review button
    Then the review is removed from the product page
    And a success message is displayed.