package aviasales.request;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetHotelsResponse {
    static String baseURI = "http://engine.hotellook.com/api/v2/static/amenities/";
    static String languageEndpoint = "en.json";
    static String language = ".json";

    public static Response getHotelsAmenities() {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .queryParam("token", "5b80061b101731827cd0fec6aa164e79")
                .log().all()
                .when()
                .get(baseURI + languageEndpoint)
                .thenReturn();
        return response;
    }
}
