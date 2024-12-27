package restful.request;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import restful.response.GetBookingResponse;

public class GetBookingRequests {
    static String baseURI = "https://restful-booker.herokuapp.com/booking/";

    public static Response getAllBookings() {
        Response response = RestAssured.given()
                .log().all()
                .when()
                .get(baseURI)
                .thenReturn();
        return response;
    }

    public static Response createBooking(GetBookingResponse getBookingResponse){
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .body(getBookingResponse)
                .post(baseURI)
                .thenReturn();
        return response;
    }

    public static Response getBookingByID(int id) {
        Response response = RestAssured.given()
                .log().all()
                .when()
                .get(baseURI + id)
                .thenReturn();
        return response;
    }

    public static Response createBookingWithObjectMapper(String body) {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(body)
                .log().all()
                .when()
                .post(baseURI)
                .thenReturn();
        return response;
    }
}






