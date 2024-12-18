package restful.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetBookingResponse {
    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private BookingDates bookingdates;
    private String additionalneeds;

    @Data
    @JsonIgnoreProperties
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class BookingDates {
        private String checkin;
        private String checkout;
    }
}

/*
    {
        "firstname" : "Jim",
        "lastname" : "Brown",
        "totalprice" : 111,
        "depositpaid" : true,
        "bookingdates" : {
                "checkin" : "2018-01-01",
                "checkout" : "2019-01-01"
            },
        "additionalneeds" : "Breakfast"
     }
*/
