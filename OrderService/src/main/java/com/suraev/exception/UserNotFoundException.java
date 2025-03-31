package com.suraev.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String message, HttpStatus httpStatus) {
        super(message);
    }
}
