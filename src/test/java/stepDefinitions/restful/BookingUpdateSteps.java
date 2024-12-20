package stepDefinitions.restful;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import restful.response.AuthorizationResponse;
import restful.response.GetBookingResponse;

import java.util.List;
import java.util.Map;

import static restful.request.AuthorizationRequests.*;

public class BookingUpdateSteps {
    AuthorizationResponse authorizationResponse = new AuthorizationResponse();
    GetBookingResponse getBookingResponse = new GetBookingResponse();
    GetBookingResponse.BookingDates bookingDates = new GetBookingResponse.BookingDates();
    Response response;

    private String token;
    private int firstBookingID;

    @Given("the user generated one time token")
    public void the_user_generated_one_time_token() {
        authorizationResponse.setUsername("admin");
        authorizationResponse.setPassword("password123");

        response = generateToken(authorizationResponse);

        authorizationResponse = response.as(AuthorizationResponse.class);

        token = "token=" + authorizationResponse.getToken();
    }

    @Given("the user is get first booking from list")
    public void the_user_is_get_first_booking_from_list() {
        response = getAllBookings();

        List<GetBookingResponse> bookingResponses = response.as(new TypeRef<>() {});
        getBookingResponse = bookingResponses.get(0);

        firstBookingID = getBookingResponse.getBookingid();
    }

    @When("the user is set a new booking details request body:")
    public void the_user_is_set_a_new_booking_details_request_body(Map<String, String> bookingDetails) {
        getBookingResponse.setFirstname(bookingDetails.get("firstname"));
        getBookingResponse.setLastname(bookingDetails.get("lastname"));
        getBookingResponse.setTotalprice(Integer.parseInt(bookingDetails.get("totalprice")));
        getBookingResponse.setDepositpaid(Boolean.parseBoolean(bookingDetails.get("depositpaid")));
        getBookingResponse.setAdditionalneeds(bookingDetails.get("additionalneeds"));

        bookingDates.setCheckin(bookingDetails.get("checkin"));
        bookingDates.setCheckout(bookingDetails.get("checkout"));

        getBookingResponse.setBookingdates(bookingDates);
        getBookingResponse.setBookingid(null);
    }

    @Then("the user is updating the existing booking using PUT call")
    public void the_user_is_updating_the_existing_booking_using_put_call() {
        response = updateBooking(token, getBookingResponse, firstBookingID);
        response.prettyPrint();
    }

    @When("the user is partially set a new booking details request body:")
    public void the_user_is_partially_set_new_booking_details(Map<String, String> bookingDetails) {
        getBookingResponse.setBookingid(null);
        getBookingResponse.setFirstname(bookingDetails.get("firstname"));
    }

    @Then("the user is updating the existing booking using PATCH call")
    public void the_user_is_updating_the_existing_booking_using_patch_call() {
        response = partiallyUpdateBooking(getBookingResponse, token, firstBookingID);
        response.prettyPrint();
    }
}
