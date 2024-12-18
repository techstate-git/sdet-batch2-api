package fakerestapi.requests;

import fakerestapi.response.ActivitiesResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ActivitiesRequest {
    static String base_URI = "https://fakerestapi.azurewebsites.net";
    static String endpoint = "/api/v1/Activities/";

    public static Response getActivities() {
        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .log().all()
                .when()
                .get(base_URI + endpoint)
                .thenReturn();
        return response;
    }

    public static Response getActivitiesByID(int id) {
        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .log().all()
                .when()
                .get(base_URI + endpoint + id)
                .thenReturn();
        return response;
    }

    public static Response postActivity(ActivitiesResponse activities) {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(activities)
                .log().all()
                .when()
                .post(base_URI + endpoint)
                .thenReturn();
        return response;
    }
}















