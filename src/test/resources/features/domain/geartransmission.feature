Feature: Gear Transmission

  Background:
    Given we have a gear transmission with 6 gears

  Scenario: shift up successfully
    When we shift up 6 times
    Then the gear transmission has current gear of 6

  Scenario: shift up until error
    When we shift up 7 times
    Then the shift up fails
    And the gear transmission has current gear of 6
