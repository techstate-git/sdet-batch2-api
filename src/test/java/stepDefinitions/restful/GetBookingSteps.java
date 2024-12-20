package stepDefinitions.restful;

import fakerestapi.response.AuthorsResponse;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import restful.response.AuthorizationResponse;
import restful.response.GetBookingResponse;

import java.util.Map;

import static restful.request.AuthorizationRequests.generateToken;
import static restful.request.GetBookingRequests.*;

public class GetBookingSteps {
    Response response;
    GetBookingResponse getBookingResponse = new GetBookingResponse();
    GetBookingResponse.BookingDates bookingDates = new GetBookingResponse.BookingDates();
    AuthorizationResponse authorizationResponse = new AuthorizationResponse();

    static int createdBookingID;
    private String token;

    @Given("the user is get all bookings")
    public void the_user_is_get_all_bookings() {
        getAllBookings().prettyPrint();
    }

    @Given("the request body contains the following booking details:")
    public void the_request_body_contains_the_following_booking_details(Map<String, String> bookingDetails) {
        getBookingResponse.setFirstname(bookingDetails.get("firstname"));
        getBookingResponse.setLastname(bookingDetails.get("lastname"));
        getBookingResponse.setTotalprice(Integer.parseInt(bookingDetails.get("totalprice")));
        getBookingResponse.setDepositpaid(Boolean.parseBoolean(bookingDetails.get("depositpaid")));
        getBookingResponse.setAdditionalneeds(bookingDetails.get("additionalneeds"));

        bookingDates.setCheckin(bookingDetails.get("checkin"));
        bookingDates.setCheckout(bookingDetails.get("checkout"));

        getBookingResponse.setBookingdates(bookingDates);
    }

    @When("I send a POST request to create a booking")
    public void i_send_a_post_request_to_create_a_booking() {
        response = createBooking(getBookingResponse);
        response.prettyPrint();
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(int statusCode) {
        Assert.assertEquals(statusCode, response.getStatusCode());
    }

    @Then("the response body should contain a bookingid field")
    public void the_response_body_should_contain_a_bookingid_field() {
        getBookingResponse = response.as(GetBookingResponse.class);
        createdBookingID = getBookingResponse.getBookingid();
        Assert.assertNotNull("Booking ID is null", createdBookingID);
    }

    @Then("the user is get booking details by ID")
    public void the_user_is_get_booking_details_by_id() {
        response = getBookingByID(createdBookingID);
    }

    @Then("the user is verified firstname {string} and lastname {string}")
    public void the_user_is_verified_firstname_and_lastname(String firstName, String lastName) {
        getBookingResponse = response.as(GetBookingResponse.class);

        Assert.assertEquals(getBookingResponse.getFirstname(), firstName);
        Assert.assertEquals(getBookingResponse.getLastname(), lastName);
    }
}
















