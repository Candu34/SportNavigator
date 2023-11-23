package com.example.sportnavigator.DTO;

import com.example.sportnavigator.Models.Image;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {

    @Email(message = "wrong email format")
    @JsonProperty
    private String email;

    @NotNull(message = "name should not be empty")
    @Size(min = 2, max = 30, message = "name should be between 2 and 30 characters")
    @JsonProperty
    private String name;

    @JsonProperty
    private String password;

//    private String role;










}
