Feature: Main
  As a user, I want to perform different operations directly from the main screen.

  Background: User has logged in already
    Given main screen is opened

  Scenario: Open The Customers Panel
    When I click on customers panel
    Then customers panel opens

  Scenario: Open The Orders Panel
    When I click on orders panel
    Then orders panel opens

  Scenario: Logout
    When I click on logout button
    Then main screen switches to login screen
