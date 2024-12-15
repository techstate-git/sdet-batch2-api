Feature: Test users for store api

  Scenario: Successfully add a new user to store api
    Given the store API endpoint is available to add new user
    And a valid user payload is ready
    When I send a request to add the new user
    Then the response returns the new user id