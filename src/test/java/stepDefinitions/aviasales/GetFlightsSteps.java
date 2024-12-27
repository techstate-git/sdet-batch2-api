package stepDefinitions.aviasales;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.cienvironment.internal.com.eclipsesource.json.Json;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;

import javax.print.attribute.standard.JobName;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static aviasales.request.GetFlightsRequest.getCheapFlight;

public class GetFlightsSteps {
    Response response;

    private String from;
    private String to;
    private String departDate;
    private String returnDate;
    private String currency;

    String responseAirline;
    String responseExpires;
    int responsePrice;
    int responseFlightNumber;

    double USD;
    double RUB;

    @Given("the user is get cheap flight from {string} to {string}")
    public void the_user_is_get_cheap_flight_from_to(String from, String to) {
        this.from = from;
        this.to = to;
    }

    @When("the user select depart date {string} and return date {string}")
    public void the_user_select_depart_date_and_return_date(String departDate, String returnDate) {
        this.departDate = departDate;
        this.returnDate = returnDate;
    }

    @When("the user choose currency {string}")
    public void the_user_choose_currency(String currency) {
        this.currency = currency;
    }

    @Then("the response code is {int}")
    public void the_response_code_is(int statusCode) {
        response = getCheapFlight(from, to, departDate, returnDate, currency);
        response.prettyPrint();
        Assert.assertEquals(statusCode, response.getStatusCode());
    }

    @Then("the user convert JSON into Java")
    public void the_user_convert_json_into_java() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String responseBody = response.getBody().asString();

            JsonNode rootNode = objectMapper.readTree(responseBody);

            JsonNode dataNode = rootNode.get("data");
            System.out.println(dataNode.toPrettyString());

            JsonNode chiNode = dataNode.get("CHI");
            System.out.println(chiNode.toPrettyString());

            JsonNode flightDetailsNode = chiNode.get("2");
            System.out.println(flightDetailsNode.toPrettyString());

            responseAirline = flightDetailsNode.get("airline").asText();
            responseExpires = flightDetailsNode.get("expires_at").asText();
            responsePrice = flightDetailsNode.get("price").asInt();
            responseFlightNumber = flightDetailsNode.get("flight_number").asInt();

            System.out.println(responseAirline);
            System.out.println(responseExpires);
            System.out.println(responsePrice);
            System.out.println(responseFlightNumber);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Then("the response contains the airline {string} and flight number {int}")
    public void the_response_contains_the_airline_and_flight_number(String airline, int flight_number) {
        if (responseAirline.contains(airline) && responseFlightNumber == flight_number) {
            System.out.println("The response is matched with airline and flight number");
        } else {
            System.out.println("The response does not matched");
        }
    }

    @Then("the price is greater than {int}")
    public void the_price_is_greater_than(int price) {
        if (responsePrice > price) {
            System.out.println("Price is greater than: " + price);
        } else {
            System.out.println("Price is less than: " + price);
        }
    }

    @Then("the response includes an expiry date in the future")
    public void the_response_includes_an_expiry_date_in_the_future() {
        //2024-12-27T00:47:49Z
        //yyyy-MM-ddTHH:mm:ssZ
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        LocalDateTime expireDate = LocalDateTime.parse(responseExpires, formatter);

        LocalDateTime now = LocalDateTime.now();

        Assert.assertTrue("Expiry date is not in the future", expireDate.isAfter(now));
    }

    @Then("the user is saved {string} price")
    public void the_user_is_saved_price(String currency) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String responseBody = response.getBody().asString();

            JsonNode rootNode = objectMapper.readTree(responseBody);
            JsonNode flightDetails = rootNode.get("data").get("CHI").get("2");

            double price = flightDetails.get("price").asDouble();

            switch (currency) {
                case "USD" : USD = price;
                    System.out.println("USD: " + USD);
                    break;
                case "RUB" : RUB = price;
                    System.out.println("RUB: " + RUB);
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Then("verify currency usd to rub converted correct using rate {double}")
    public void verify_currency_usd_to_rub_converted_correct_using_rate(Double conversionRate) {
        double expectedRubPrice = USD * conversionRate;

        System.out.println("Original price in USD: " + USD);
        System.out.println("Expected Price in RUB: " + RUB);
        System.out.println("Actual Price in RUB from Response: " + expectedRubPrice);

        Assert.assertEquals(expectedRubPrice, RUB, 0.01);
    }
}












