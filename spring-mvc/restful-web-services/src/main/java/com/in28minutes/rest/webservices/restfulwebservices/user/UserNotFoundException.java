package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This will allow Spring to return a 404 response if this exception gets thrown from
 * our controllers
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(int id) {
        super("User " + id + " not found");
    }
}
