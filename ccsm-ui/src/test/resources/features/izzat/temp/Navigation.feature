Feature: Navigation
  As a user, I want to perform different operations and navigate between different screens.
  this can be achieved using the different navigation tabs and buttons.

  Background: User has logged in already
    Given main screen is opened

  Scenario: Logout
    When I click on logout button
    Then main screen switches to login screen
