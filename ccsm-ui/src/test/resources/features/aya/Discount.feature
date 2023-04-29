Feature: Discount eligibility check

  Scenario: Eligible for discount
    Given a total amount of 500 NIS
    When the customer checks for discount eligibility
    Then the system should display the discount message

  Scenario: Not eligible for discount
    Given a total amount of 200 NIS
    When the customer checks for discount eligibility
    Then the system should display the no-discount message

  Scenario: Borderline eligibility for discount
    Given a total amount of 400 NIS
    When the customer checks for discount eligibility
    Then the system should display the no-discount message

  Scenario: Eligible for discount with decimal amount
    Given a total amount of 820.5 NIS
    When the customer checks for discount eligibility
    Then the system should display the discount message