package com.twitterapi.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalRestControllerAdviceExceptionHandler {

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)

    public CustomErrorDetails usernameAlreadyExists(UsernameAlreadyExistsException ex){
        return new CustomErrorDetails(new Date(), "Username already exists !", ex.getMessage());
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)

    public CustomErrorDetails emailAlreadyExists(EmailAlreadyExistsException ex){
        return new CustomErrorDetails(new Date(), "Email already exists !", ex.getMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)

    public CustomErrorDetails usernameNotFound(UsernameNotFoundException ex) {
        return new CustomErrorDetails(new Date(),"User not found.", ex.getMessage());
    }

    @ExceptionHandler(TweetUpdateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomErrorDetails tweetUpdate(TweetUpdateException ex) {
        return new CustomErrorDetails(new Date(), "Only tweets can be updated", ex.getMessage());
    }

    @ExceptionHandler(TweetIdNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CustomErrorDetails tweetIdNotFound(TweetIdNotFoundException ex){
        return new CustomErrorDetails(new Date(), "Tweet Id Not Found", ex.getMessage());
    }
}
