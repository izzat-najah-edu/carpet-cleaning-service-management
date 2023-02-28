Feature: Add Customer
  it's always expected to get new customers, so the system should be able
  to expand it's database and add new customers.

  Background: User reached the Add Customer Tab
    Given main screen is opened
    And customers panel is opened
    And add customer tab is opened

  Scenario: Adding Customer Successfully
