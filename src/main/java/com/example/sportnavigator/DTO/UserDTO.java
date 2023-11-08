package com.example.sportnavigator.DTO;

import com.example.sportnavigator.Models.Image;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {

    @Email(message = "Email should not be empty")
    private String email;

    private String name;

    private Image avatar;

    private String password;

    private String role;








}
