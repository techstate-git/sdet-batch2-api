package stepDefinitions.fakerestapi;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

public class StoreApiUsers {

    private String userPayload;
    private Response response;

    @Given("^the store API endpoint is available to add new user$")
    public void storeApiEndpointIsAvailable() {
        RestAssured.baseURI = "https://fakestoreapi.com";
    }

    @And("^a valid user payload is ready$")
    public void validUserData() {
        userPayload = "{\n" +
                "   \"email\":\"Bak@gmail.com\",\n" +
                "   \"username\":\"bak\",\n" +
                "   \"password\":\"abcd1234$\",\n" +
                "   \"name\":{\n" +
                "      \"firstname\":\"Bak\",\n" +
                "      \"lastname\":\"Johnson\"\n" +
                "   },\n" +
                "   \"address\":{\n" +
                "      \"city\":\"kilcoole\",\n" +
                "      \"street\":\"7835 new road\",\n" +
                "      \"number\":3,\n" +
                "      \"zipcode\":\"12926-3874\",\n" +
                "      \"geolocation\":{\n" +
                "         \"lat\":\"-37.3159\",\n" +
                "         \"long\":\"81.1496\"\n" +
                "      }\n" +
                "   },\n" +
                "   \"phone\":\"1-570-236-7033\"\n" +
                "}";
    }

    @When("^I send a request to add the new user$")
    public void iSendRequestToAddNewUser() {
        String endpoint = "/users";
        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(userPayload)
                .when()
                .post(endpoint);
    }

    @Then("^the response returns the new user id$")
    public void responseReturnsNewUserId() {
        int actualStatusCode = response.getStatusCode();
        Assert.assertEquals(200, actualStatusCode);
        System.out.println(actualStatusCode);

        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("id"));
        response.prettyPrint();
    }

}
