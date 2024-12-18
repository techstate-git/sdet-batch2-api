Feature: Test Activities

  Scenario: Successful GET Activity
    Given the user is get activities

  Scenario: Successful GET Activity by ID
    Given the user is get activities by id = 3

  Scenario: Successful POST new Activity
    Given the user is created activity with id = 777
    And the title is = "Techstate"
    And the due date is = "2024-12-17T00:12:33.803Z"
    And activity is completed = true
    Then the user is creating activity using parameters above