Feature: Login
  As a user, I want to log in to the program so that I can access my account.
  The application should have a special login panel to control authorization.

  Background: User has not logged in yet
    Given login screen is opened

  Scenario: Successful Login
    When I enter correct username and correct password
    And I click on the login button
    Then login screen switches to main screen

  Scenario: Failed Login
    When I enter wrong username or wrong password
    And I click on the login button
    Then error message indicating failed login is prompted
