package com.example.sportnavigator.DTO;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SportCourtDTO {

    @JsonProperty
    @NotNull
    @Size(min = 2, max = 50)
    private String name;

    @JsonProperty
    @NotNull
    private String description;

    @JsonProperty
    @NotNull
    private String courtType;

    @JsonProperty
    private List<EncodedImage> images;

    @JsonProperty
    @NotNull
    private Long userID;

    @JsonProperty
    @Min(0)
    @Max(90)
    private double latitude;

    @Min(0)
    @Max(90)
    @JsonProperty
    private double longitude;

}
