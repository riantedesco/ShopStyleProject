package com.compass.msaudit.configuration.handler;

import com.compass.msaudit.exception.NotFoundAttributeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@org.springframework.web.bind.annotation.ControllerAdvice(basePackages = "com.compass.msaudit.controller")
public class ControllerAdvice {

    @ResponseBody
    @ExceptionHandler(NotFoundAttributeException.class)
    public ResponseEntity<MessageExceptionHandler> notFoundAttributeException(NotFoundAttributeException notFoundAttributeException) {
        MessageExceptionHandler error = new MessageExceptionHandler(
                new Date(), HttpStatus.NOT_FOUND.value(), "There's data in the request that doesn't match with the database");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageExceptionHandler> methodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        MessageExceptionHandler error = new MessageExceptionHandler(
                new Date(), HttpStatus.BAD_REQUEST.value(), "There's data in the body that are not correct or not allowed");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
