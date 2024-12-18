package fakerestapi.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class ActivitiesResponse {
    private int id;
    private String title;
    private String dueDate;
    private boolean completed;
}
