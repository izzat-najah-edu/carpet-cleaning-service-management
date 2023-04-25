Feature: Logout
  As a user, I want to log out from the program so that my account can be safely closed and my data is protected.

  Scenario: Successful Logout
    Given that the user is logged in
    When the user clicks on the logout button
    Then the user session is terminated
    And the user is redirected to the login screen

  Scenario: Logout without Login
    Given that the user is not logged in
    When the user clicks on the logout button
    Then an error message indicating the user is not logged in is prompted

  Scenario: Logout after session timeout
    Given that the user is logged in but the session has timed out
    When the user clicks on the logout button
    Then the user session is terminated
    And the user is redirected to the login screen
    And an error message indicating the session has timed out is prompted.