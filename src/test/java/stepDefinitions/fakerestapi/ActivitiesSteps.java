package stepDefinitions.fakerestapi;

import io.cucumber.java.en.Given;
import io.restassured.response.Response;
import org.junit.Assert;

import static fakerestapi.requests.ActivitiesRequest.getActivities;
import static fakerestapi.requests.ActivitiesRequest.getActivitiesByID;

public class ActivitiesSteps {
    @Given("the user is get activities")
    public void the_user_is_get_activities() {
        Response response = getActivities();
        response.prettyPrint();
        Assert.assertEquals(200, response.getStatusCode());
    }

    @Given("the user is get activities by id = {int}")
    public void the_user_is_get_activities_by_id(int id) {
        Response response = getActivitiesByID(id);
        response.prettyPrint();
        Assert.assertEquals(200, response.getStatusCode());
    }
}
