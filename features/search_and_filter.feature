Feature: Search and Filter Validation on Amazon.in

  Scenario: Search for a phone, apply filters, and validate the results
    Given User is on Amazon.in homepage
    When User searches for "phone"
    And User applies filters
    Then Validate the filtered list of phones
