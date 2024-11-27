Feature: Vendor API

  Scenario: Get all vendors
    Given we have vendor "FORD" with name "Ford" and city "Cologne"
    When we fetch all vendors
    Then we get a result of size 1
    And the result contains a vendor "FORD" with name "Ford" and city "Cologne"

  Scenario: Save invalid vendor
    When we save a vendor "FORD" without any information
    Then we get a validation error
