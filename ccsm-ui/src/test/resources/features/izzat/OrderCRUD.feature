Feature: Order CRUD Operations
  A customer may have different orders.
  So there should be the option to create and delete orders.

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
