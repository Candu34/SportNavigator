package com.example.sportnavigator.util.exceptions;

public class UserExistingEmailException extends RuntimeException{
    public UserExistingEmailException(String msg){
        super(msg);
    }
}
