package com.ashopnotifications.handler;

import com.ashopnotifications.exception.OrderNotFoundException;
import com.ashopnotifications.exception.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    private static final Logger log = LoggerFactory.getLogger(ControllerAdvice.class);

    @ExceptionHandler(OrderNotFoundException.class)
    public String handleOrderNotFoundException(OrderNotFoundException ex) {
        log.error(ex.getMessage());
        return String.format("Order not found %s", ex.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public String handleUserNotFoundException(UserNotFoundException ex) {
        log.error(ex.getMessage());
        return String.format("User not found %s", ex.getMessage());
    }
}
