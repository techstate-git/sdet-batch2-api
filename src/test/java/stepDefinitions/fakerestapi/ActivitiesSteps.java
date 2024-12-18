package stepDefinitions.fakerestapi;

import fakerestapi.response.ActivitiesResponse;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.Assert;

import java.lang.reflect.Type;
import java.util.List;

import static fakerestapi.requests.ActivitiesRequest.*;

public class ActivitiesSteps {
    ActivitiesResponse activities = new ActivitiesResponse();

    @Given("the user is get activities")
    public void the_user_is_get_activities() {
        Response response = getActivities();
        response.prettyPrint();
        Assert.assertEquals(200, response.getStatusCode());

        List<ActivitiesResponse> activitiesList = response.as(new TypeRef<>() {});

        int sum = 0;

        for (ActivitiesResponse activities : activitiesList) {
            sum += activities.getId();
        }

        System.out.println(sum);
    }

    @Given("the user is get activities by id = {int}")
    public void the_user_is_get_activities_by_id(int id) {
        Response response = getActivitiesByID(id);
        response.prettyPrint();
        Assert.assertEquals(200, response.getStatusCode());
    }

    @Given("the user is created activity with id = {int}")
    public void the_user_is_created_activity_with_id(int id) {
        activities.setId(id);
    }

    @Then("the user is creating activity using parameters above")
    public void the_user_is_creating_activity_using_parameters_above() {
        Response response = postActivity(activities);
        System.out.println("Response: \n");
        response.prettyPrint();
        Assert.assertEquals(200, response.getStatusCode());
    }

    @Given("the title is = {string}")
    public void the_title_is(String title) {
        activities.setTitle(title);
    }

    @Given("the due date is = {string}")
    public void the_due_date_is_days(String dueDate) {
        activities.setDueDate(dueDate);
    }

    @Given("activity is completed = {booleanType}")
    public void activity_is_completed_true(boolean isCompleted) {
        activities.setCompleted(isCompleted);
    }

    @ParameterType("true|false")
    public boolean booleanType(String value){
        return Boolean.parseBoolean(value);
    }
}















