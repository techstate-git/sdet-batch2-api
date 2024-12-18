package fakerestapi.requests;

import fakerestapi.response.AuthorsResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class AuthorRequests {
    static String authorsBaseURI = "https://fakerestapi.azurewebsites.net/api/v1/Authors";

    public static Response getAuthors() {
        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .log().all()
                .when()
                .get(authorsBaseURI)
                .thenReturn();
        return response;
    }

    public static Response postAuthors(AuthorsResponse authors) {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(authors)
                .log().all()
                .when()
                .post(authorsBaseURI)
                .thenReturn();
        return response;
    }
}
