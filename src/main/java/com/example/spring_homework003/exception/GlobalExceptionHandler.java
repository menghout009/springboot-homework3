package com.example.spring_homework003.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    ProblemDetail userNotFoundException(UserNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND, e.getMessage()
        );
        problemDetail.setType(URI.create("http://localhost:8989/api/v1/users/not-found"));
        problemDetail.setTitle("NOT FOUND");
        problemDetail.setDetail("USER INPUT NOTHING EXIST...!");
        problemDetail.setProperty("Timestamp", Instant.now());
        return problemDetail;
    }

    @ExceptionHandler(UserBadRequestException.class)
    ProblemDetail userBadRequestException(UserBadRequestException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST, e.getMessage()
        );
        problemDetail.setType(URI.create("http://localhost:8989/api/v1/users/not-badrequest"));
        problemDetail.setTitle("INPUT BAD REQUEST");
        problemDetail.setDetail("USER INPUT SOMETHING INCORRECTLY !");
        problemDetail.setProperty("Timestamp", Instant.now());

        return problemDetail;
    }
}
