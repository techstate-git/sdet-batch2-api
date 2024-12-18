package stepDefinitions.restful;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import restful.response.GetBookingResponse;

import java.util.Map;

import static restful.request.GetBookingRequests.createBooking;
import static restful.request.GetBookingRequests.getAllBookings;

public class GetBookingSteps {
    Response response;
    GetBookingResponse getBookingResponse = new GetBookingResponse();
    GetBookingResponse.BookingDates bookingDates = new GetBookingResponse.BookingDates();

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

    }
}
