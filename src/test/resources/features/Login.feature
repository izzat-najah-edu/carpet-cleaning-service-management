Feature: Login
  As a user, I want to log in to the program so that I can access my account.

  Scenario: Successful Login
    Given the login screen is displayed
    When I enter the correct username and the correct password
    Then the login screen closes
    And the main screen opens

  Scenario: Failed Login
    Given the login screen is displayed
    When I enter a wrong username or a wrong password
    Then an error message indicating a failed login is displayed

  Scenario: Exit
    Given the login screen is displayed
    When I click the exit button
    Then the login screen closes
