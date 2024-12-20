Feature: Update a existing booking using the Booking API
  As a QA Engineer
  I want to send a PUT/PATCH/DELETE request to update the booking
  So that I can verify existing booking is successfully update with correct details

  Scenario: Update existing Booking
    Given the user generated one time token

  Scenario: Update the existing Booking ID
    Given the user generated one time token
    And the user is get first booking from list
    When the user is set a new booking details request body:
      | firstname       | UpdatedFirstName |
      | lastname        | UpdatedLastName  |
      | totalprice      | 2900             |
      | depositpaid     | true             |
      | checkin         | 2024-12-31       |
      | checkout        | 2025-01-05       |
      | additionalneeds | Samsa            |
    Then the user is updating the existing booking using PUT call

  Scenario: Partially update the existing Booking ID
    Given the user generated one time token
    Given the user is get first booking from list
    When the user is partially set a new booking details request body:
      | firstname       | UpdatedFirstName |
    Then the user is updating the existing booking using PATCH call