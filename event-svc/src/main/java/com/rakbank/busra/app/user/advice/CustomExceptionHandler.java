package com.rakbank.busra.app.user.advice;

import com.rakbank.busra.app.user.exceptions.EventNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(EventNotFoundException.class)
    public ErrorResponse handleInvalidArgument(EventNotFoundException ex) {
        return ErrorResponse.create(ex, HttpStatus.BAD_REQUEST, ex.getMessage());
    }
}
