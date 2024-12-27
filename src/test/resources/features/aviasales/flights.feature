Feature: Test Flights API

  Scenario: Successful GET cheapest flights by destination
    Given the user is get cheap flight from "IST" to "ORD"
    When the user select depart date "2025-03" and return date "2025-04"
    And the user choose currency "usd"
    Then the response code is 200
    And the user convert JSON into Java
    And the response contains the airline "BA" and flight number 603
    And the price is greater than 0
    And the response includes an expiry date in the future

  Scenario: Verify currency converted
    Given the user is get cheap flight from "IST" to "ORD"
    When the user select depart date "2025-03" and return date "2025-04"
    And the user choose currency "usd"
    Then the response code is 200
    And the user is saved "USD" price
    Given the user is get cheap flight from "IST" to "ORD"
    When the user select depart date "2025-03" and return date "2025-04"
    And the user choose currency "rub"
    Then the response code is 200
    And the user is saved "RUB" price
    And verify currency usd to rub converted correct using rate 103.60
