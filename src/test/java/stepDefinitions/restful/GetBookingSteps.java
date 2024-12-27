package stepDefinitions.restful;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
    private String jsonString;

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

    @Given("the request body with ObjectMapper contains the following booking details:")
    public void the_request_body_with_object_mapper_contains_the_following_booking_details(Map<String, String> bookingDetails) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            ObjectNode rootNode = objectMapper.createObjectNode();

            rootNode.put("firstname", bookingDetails.get("firstname"));
            rootNode.put("lastname", bookingDetails.get("lastname"));
            rootNode.put("totalprice", Integer.parseInt(bookingDetails.get("totalprice")));
            rootNode.put("depositpaid", Boolean.parseBoolean(bookingDetails.get("depositpaid")));
            rootNode.put("additionalneeds", bookingDetails.get("additionalneeds"));

            // Create 'bookingdates' object
            ObjectNode bookingDatesNode = objectMapper.createObjectNode();
            bookingDatesNode.put("checkin", bookingDetails.get("checkin"));
            bookingDatesNode.put("checkout", bookingDetails.get("checkout"));

            // Add 'bookingdates' to root
            rootNode.set("bookingdates", bookingDatesNode);

            // Convert the ObjectNode to a JSON string
            jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @When("I send a POST request to create a booking with map")
    public void i_send_a_post_request_to_create_a_booking_with_map() {
        response = createBookingWithObjectMapper(jsonString);
    }

    @Then("I validate the response")
    public void i_validate_the_response() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            String responseBody = response.getBody().asString();

            JsonNode rootNode = objectMapper.readTree(responseBody);
            int bookingid = rootNode.get("bookingid").asInt();

            JsonNode bookingNode = rootNode.get("booking");

            String firstname = bookingNode.get("firstname").asText();
            String lastname = bookingNode.get("lastname").asText();
            int totalPrice = bookingNode.get("totalprice").asInt();
            boolean depositpaid = bookingNode.get("depositpaid").asBoolean();

            JsonNode bookingdates = bookingNode.get("bookingdates");
            String checkin = bookingdates.get("checkin").asText();
            String checkout = bookingdates.get("checkout").asText();

            System.out.println(bookingid);
            System.out.println(firstname);
            System.out.println(lastname);
            System.out.println(totalPrice);
            System.out.println(depositpaid);
            System.out.println(checkin);
            System.out.println(checkout);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
















