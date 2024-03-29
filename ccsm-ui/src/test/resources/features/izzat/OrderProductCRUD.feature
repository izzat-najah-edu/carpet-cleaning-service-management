Feature: Order Product CRUD Operations
  An order of a customer may have different products.
  So there should be the option to perform CRUD operations on the products of each of the orders.

  Background: User reached orders tab
    Given main screen is opened
    And orders tab is opened
    And I select a customer from the customer box
    And I select an order from the order list

  Scenario: Adding Product to an Order Successfully
    Given I enter order product information
    And I select a product from the product box
    And I click add order product button
    Then a new product is added to the order product list

  Scenario: Updating Product of an Order Successfully
    Given I select a product from the order product list
    And I enter a different order product information
    And I click update order product button
    Then the selected order product is updated

  Scenario: Deleting Product of an Order Successfully
    Given I select a product from the order product list
    And I click delete order product button
    Then the selected order product is deleted
