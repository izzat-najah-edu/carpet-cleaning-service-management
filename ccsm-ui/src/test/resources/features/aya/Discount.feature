Feature: Discount Feature
  As a user, I want to be able to apply a discount code to my purchase so that I can get a reduced price.

  Scenario: Successful Discount Application
    Given that the user has added items to their cart
    And the user has a valid discount code
    When the user enters the discount code and clicks apply
    Then the discount is applied to the purchase total
    And the user sees the discounted price in their cart
    And the user can proceed with the purchase

  Scenario: Invalid Discount Code
    Given that the user has added items to their cart
    And the user has entered an invalid discount code
    When the user clicks apply
    Then an error message indicating the code is invalid is displayed
    And the discount is not applied to the purchase total

  Scenario: Expired Discount Code
    Given that the user has added items to their cart
    And the user has entered a discount code that has expired
    When the user clicks apply
    Then an error message indicating the code has expired is displayed
    And the discount is not applied to the purchase total

  Scenario: Discount Code Not Applicable
    Given that the user has added items to their cart
    And the user has entered a valid discount code
    But the code is not applicable to the items in the cart
    When the user clicks apply
    Then an error message indicating the code cannot be applied is displayed
    And the discount is not applied to the purchase total.