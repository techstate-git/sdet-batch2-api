package restful.request;

import fakerestapi.response.AuthorsResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import restful.response.AuthorizationResponse;

public class AuthorizationRequests {
    static String baseURI = "https://restful-booker.herokuapp.com/auth";

    public static Response generateToken(AuthorizationResponse authorizationResponse) {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .body(authorizationResponse)
                .post(baseURI)
                .thenReturn();
        return response;
    }
}
