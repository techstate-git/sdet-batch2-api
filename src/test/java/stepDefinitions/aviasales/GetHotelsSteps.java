package stepDefinitions.aviasales;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

import java.util.function.DoubleToIntFunction;

import static aviasales.request.GetHotelsResponse.getHotelsAmenities;

public class GetHotelsSteps {
    Response response;

    @Given("the user is get all amenities")
    public void the_user_is_get_all_amenities() {
        response = getHotelsAmenities();
    }

    @Then("the user print all amenities names")
    public void the_user_print_all_amenities_names() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String responseBody = response.getBody().asString();

            JsonNode rootArray = objectMapper.readTree(responseBody);
            System.out.println(rootArray.get(0).toPrettyString());

            for (JsonNode node : rootArray) {
                System.out.println(node.get("name").asText());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Then("the user print amenities which grouped by {string}")
    public void the_user_print_amenities_which_grouped_by(String group) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String responseBody = response.getBody().asString();

            JsonNode rootArray = objectMapper.readTree(responseBody);

            for (JsonNode node : rootArray) {
                String groupName = node.get("groupName").asText();
                if (group.equals(groupName)) {
                    System.out.println(node.get("name").asText());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
