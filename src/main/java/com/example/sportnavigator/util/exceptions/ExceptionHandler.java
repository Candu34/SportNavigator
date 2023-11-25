package com.example.sportnavigator.util.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {


    @org.springframework.web.bind.annotation.ExceptionHandler(value = UserNotCreatedException.class)
    public ResponseEntity<ErrorMessage> handleUserNotCreatedException(UserNotCreatedException e){
        ErrorMessage userErrorMessage = new ErrorMessage(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(userErrorMessage, HttpStatus.CONFLICT);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = CourtNotCreatedException.class)
    public ResponseEntity<ErrorMessage> handleCourtNotCreatedException(CourtNotCreatedException e){
        ErrorMessage ErrorMessage = new ErrorMessage(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(ErrorMessage, HttpStatus.CONFLICT);
    }






}
