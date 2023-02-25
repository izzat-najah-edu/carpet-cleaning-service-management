Feature: Main
  As a user, I want to perform different operations directly from the main screen.

  Scenario: Open The Customers Panel
    Given the main screen is opened
    When I click on the customers panel
    Then the customers panel opens

  Scenario: Open The Orders Panel
    Given the main screen is opened
    When I click on the orders panel
    Then the orders panel opens

  Scenario: Exit
    Given the main screen is opened
    When I click the exit button
    Then the main screen closes
