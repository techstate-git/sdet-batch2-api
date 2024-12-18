Feature: Create a new booking using the Booking API
  As a QA Engineer
  I want to send a POST request to create a booking
  So that I can verify a new booking is successfully created with correct details

  Scenario: Successful GET all bookings
    Given the user is get all bookings

  Scenario: Successfully create a new booking with valid details
    Given the request body contains the following booking details:
      | firstname       | BeK        |
      | lastname        | Smith      |
      | totalprice      | 200        |
      | depositpaid     | true       |
      | checkin         | 2019-01-01 |
      | checkout        | 2020-01-01 |
      | additionalneeds | Breakfast  |
    When I send a POST request to create a booking
    Then the response status code should be 200
    And the response body should contain a bookingid field
    And the user is get booking details by ID
    And the user is verified firstname "BeK" and lastname "Smith"

  Scenario: Generate Authorization Token
    Given the user generated one time token