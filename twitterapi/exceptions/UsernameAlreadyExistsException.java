package com.twitterapi.exceptions;

public class UsernameAlreadyExistsException extends Exception {
    
    public UsernameAlreadyExistsException(String message){
        super(message);
    }
}