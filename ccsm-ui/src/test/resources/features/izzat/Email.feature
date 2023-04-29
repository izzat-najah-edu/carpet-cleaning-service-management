Feature: Email
  when an order is complete, the customers should know about it to come and receive it.
  So the system should have a customer email functionality for finished orders.

  Background: An order is selected
    Given main screen is opened
    And orders tab is opened
    And I select a customer from the customer box
    And I select an order from the order list

  Scenario:
