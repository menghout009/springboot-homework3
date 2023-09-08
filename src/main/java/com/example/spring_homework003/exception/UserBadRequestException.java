package com.example.spring_homework003.exception;

public class UserBadRequestException extends RuntimeException {

    public UserBadRequestException(String message) {
        super(message);

    }
}
