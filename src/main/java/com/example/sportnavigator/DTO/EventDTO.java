package com.example.sportnavigator.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDTO {

    @JsonProperty
    private String name;

    @JsonProperty
    private String description;

    @JsonProperty
    private Long sportCourtId;

    @JsonProperty
    private String event_time;
}
