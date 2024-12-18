package fakerestapi.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthorsResponse {
    private int id;
    private int idBook;
    private String firstName;
    private String lastName;
}
