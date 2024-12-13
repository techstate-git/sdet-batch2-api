package fakerestapi.requests;

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
}
