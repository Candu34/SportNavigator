package com.example.sportnavigator.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDTO {

    @JsonProperty
    private Long id;

    @JsonProperty
    @Size(min = 3, max = 60, message = "name should be between 3 and 60 characters")
    @NotNull
    private String name;

    @JsonProperty
    @NotNull
    private String description;

    @JsonProperty
    @NotNull
    private Long sportCourtId;

    @JsonProperty
    private String event_time;
}
