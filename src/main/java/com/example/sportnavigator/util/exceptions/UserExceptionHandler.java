package com.example.sportnavigator.util.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler {


    @ExceptionHandler(value = UserNotCreatedException.class)
    public ResponseEntity<UserErrorMessage> handleUserNotCreatedException(UserNotCreatedException e){
        UserErrorMessage userErrorMessage = new UserErrorMessage(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(userErrorMessage, HttpStatus.CONFLICT);
    }




}
