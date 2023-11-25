package com.example.sportnavigator.util.ExceptionHandler;

import com.example.sportnavigator.util.errorMessage.ErrorMessage;
import com.example.sportnavigator.util.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {


    @org.springframework.web.bind.annotation.ExceptionHandler(value = UserNotCreatedException.class)
    public ResponseEntity<ErrorMessage> handleUserNotCreatedException(UserNotCreatedException e){
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = CourtNotCreatedException.class)
    public ResponseEntity<ErrorMessage> handleCourtNotCreatedException(CourtNotCreatedException e){
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = SportCourtNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleCourtNotFoundException(SportCourtNotFoundException e){
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = ImageNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleCourtNotFoundException(ImageNotFoundException e){
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleUserNotFoundException(UserNotFoundException e){
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }







}
