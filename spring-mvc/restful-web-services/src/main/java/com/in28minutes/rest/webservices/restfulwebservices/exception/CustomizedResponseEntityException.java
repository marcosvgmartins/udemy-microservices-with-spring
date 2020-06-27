package com.in28minutes.rest.webservices.restfulwebservices.exception;

import java.util.Date;

import com.in28minutes.rest.webservices.restfulwebservices.user.UserNotFoundException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * ResponseEntityExceptionHandler is used to provide centralized exception control
 * The @ControllerAdvice annotation is used to ensure that CustomizedResponseEntityException
 * is used through all of the controllers
 */
@ControllerAdvice
@RestController
public class CustomizedResponseEntityException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
        ExceptionResponse response =
            new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFound(Exception ex, WebRequest request) {
        ExceptionResponse response =
            new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
    }

    /**
     * We customize this method so we can provide a proper error code and message for the
     * invalid method argument exception
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionResponse response =
            new ExceptionResponse(new Date(), "Validation failed", ex.getBindingResult().toString());
        return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
    }
}