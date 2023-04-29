Feature: Order CRUD Operations
  A customer may have different orders.
  So there should be the option to create and delete orders.
  the system should also have an email service for finished orders
  to notify customers when their orders are finished.

  Background: User reached orders tab
    Given main screen is opened
    And orders tab is opened
    And I select a customer from the customer box

  Scenario: Adding Order Successfully
    Given I click add order button
    Then a new order is added to the order list

  Scenario: Deleting Order Successfully
    Given I select the new order from the order list
    And I click delete order button
    Then the selected order is deleted

  Scenario: Order is not finished
    Given I select an order from the order list
    And some order products are not finished
    Then email button is disabled

  Scenario: Order is finished
    Given I select an order from the order list
    And all order products are finished
    Then email button is enabled

  Scenario: Notify the customer
    Given I select an order from the order list
    And all order products are finished
    And I click email button
    And I confirm sending email
    Then an email is sent to the customer
    And a success message is shown
