Feature: Test authors

  Scenario: Successful GET all Authors
    Given the user is get authors

  Scenario: Successful POST new author
    Given the user is created author with id = 777
    And the user is created book with id = 778
    And the first name is = "Stephen"
    And the last name is = "King"
    Then the user is creating author using parameters above
    And print the first and last name
