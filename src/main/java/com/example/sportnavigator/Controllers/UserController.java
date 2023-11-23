package com.example.sportnavigator.Controllers;


import com.example.sportnavigator.DTO.UserDTO;
import com.example.sportnavigator.Models.User;
import com.example.sportnavigator.Service.UserService;
import com.example.sportnavigator.mapper.MapStructMapper;
import com.example.sportnavigator.util.exceptions.UserNotCreatedException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("users/api")
public class UserController {

    private final UserService userService;
    private final MapStructMapper mapStructMapper;

    @GetMapping()
    public List<User> getAllUsers(){
        return userService.getAllUsers();

    }

    @GetMapping("/{id}")
    @ResponseBody
    public UserDTO getOne(@PathVariable("id") long id){
        return mapStructMapper.userToUserDTO(userService.getUserById(id));
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
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

        userService.saveUser(mapStructMapper.UserDTOToUser(userDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }





}
