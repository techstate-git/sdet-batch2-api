Feature: Hotel list testing

  Scenario: Print all amenities
    Given the user is get all amenities
    Then the user print all amenities names

  Scenario: Print amenities which related to group Room
    Given the user is get all amenities
    Then the user print amenities which grouped by "Room"

  Scenario Outline: Print amenities using different languages
    Given the user is get all amenities in "<language>" language
    Then the user print all amenities names

    Examples:
      | language |
      | en       |
      | ru       |
      | fr       |

  Scenario: Verify facilities and their grouping logic
    Given the user is get all amenities
    Then the response should contain a total of at least 10 facilities
    And no two facilities should have the same ID

  Scenario: Verify facilities and their grouping logic
    Given the user is get all amenities
    And the response must not contain any facility with a groupName other than "Room", "General", or "Services"
    And the response should include at least one facility per group where the id starts with "1"

  Scenario: Verify facilities and their grouping logic
    Given the user is get all amenities
    And the dataset should include at least 5 facilities for each group
    And facilities with groupName "Room" should not have names longer than 25 characters