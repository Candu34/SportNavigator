package com.example.sportnavigator.Controllers;


import com.example.sportnavigator.DTO.UserDTO;
import com.example.sportnavigator.Models.User;
import com.example.sportnavigator.Service.UserService;
import com.example.sportnavigator.util.exceptions.UserNotCreatedException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("users/api")
public class UserController {

    private final UserService userService;

    @GetMapping()
    public List<User> getAllUsers(){
        return userService.getAllUsers();

    }

    @GetMapping("/{id}")
    public User getOne(@PathVariable("id") long id){
        return userService.getUserById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> save(@RequestBody @Valid UserDTO userDTO,
                                           BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();

            for(FieldError error: errors){
                errorMsg.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append(";");
            }

            throw new UserNotCreatedException(errorMsg.toString());
        }

        userService.saveUser(createUser(userDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }


    private User createUser(UserDTO userDTO){
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());

        return user;
    }


}
