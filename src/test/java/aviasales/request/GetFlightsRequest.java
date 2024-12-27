package aviasales.request;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static utils.ExtentReportManager.logAndValidate;

public class GetFlightsRequest {
    static String baseURI = "https://api.travelpayouts.com/v1/prices/cheap";

    public static Response getCheapFlight(String from, String to, String departDate, String returnDate, String currency) {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("x-access-token", "5b80061b101731827cd0fec6aa164e79")
                .queryParam("origin", from)
                .queryParam("destination", to)
                .queryParam("depart_date", departDate)
                .queryParam("return_date", returnDate)
                .queryParam("currency", currency)
                .log().all()
                .when()
                .get(baseURI)
                .thenReturn();
        return logAndValidate(response, 200);
    }
}
