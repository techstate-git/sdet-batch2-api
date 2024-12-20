package restful.request;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import restful.response.AuthorizationResponse;
import restful.response.GetBookingResponse;

public class AuthorizationRequests {
    static String baseURIToken = "https://restful-booker.herokuapp.com/auth";
    static String baseURI = "https://restful-booker.herokuapp.com/booking/";

    public static Response generateToken(AuthorizationResponse authorizationResponse){
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .body(authorizationResponse)
                .post(baseURIToken)
                .thenReturn();
        return response;
    }

    public static Response getAllBookings() {
        Response response = RestAssured.given()
                .log().all()
                .when()
                .get(baseURI)
                .thenReturn();
        return response;
    }

    public static Response updateBooking(String token, GetBookingResponse getBookingResponse, int id) {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Cookie", token)
                .log().all()
                .when()
                .body(getBookingResponse)
                .put(baseURI + id)
                .thenReturn();
        return response;
    }

    public static Response partiallyUpdateBooking(GetBookingResponse getBookingResponse, String token, int bookingID) {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Cookie", token)
                .log().all()
                .when()
                .body(getBookingResponse)
                .patch(baseURI + bookingID)
                .thenReturn();
        return response;
    }
}
