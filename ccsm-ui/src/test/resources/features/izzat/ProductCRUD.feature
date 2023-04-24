Feature: Product CRUD Operations
  the products of the customers should are continuously changing, so there should be
  the option to perform CRUD operations on the products.

  Background: User reached the Products Tab
    Given main screen is opened
    And customers panel is opened
    And products tab is opened

  Scenario: Adding Product Successfully
    Given I enter product information
    And I click add product button
    Then a new product is added to the list

  Scenario: Updating Product Successfully
    Given I select a product from the list
    And I enter a different product information
    And I click update product button
    Then the selected product is updated

  Scenario: Deleting Product Successfully
    Given I select a product from the list
    And I click delete product button
    Then the selected product is deleted

  Scenario: Searching Product Successfully
    Given I enter product
    And I click search product button
    Then the selected product is found

