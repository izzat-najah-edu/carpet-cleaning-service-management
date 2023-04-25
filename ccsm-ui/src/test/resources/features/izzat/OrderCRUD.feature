Feature: Order CRUD Operations
  An order of a customer may have different products. And a customer may have different orders.
  So there should be the option to perform CRUD operations on the orders.

  Background: User reached orders tab
    Given main screen is opened
    And orders tab is opened

  Scenario: Adding Product to an Order Successfully
    Given I enter order product information
    And I click add order product button
    Then a new product is added to the order products list

  Scenario: Updating Product of an Order Successfully
    Given I select a product from the order products list
    And I enter a different order product information
    And I click update order product button
    Then the selected order product is updated

  Scenario: Deleting Product of an Order Successfully
    Given I select a product from the order product list
    And I click delete order product button
    Then the selected order product is deleted
