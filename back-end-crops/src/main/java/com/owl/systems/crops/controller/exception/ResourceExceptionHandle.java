package com.owl.systems.crops.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;

@ControllerAdvice
@RestController
public class ResourceExceptionHandle {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardError> exceptionsError(Exception e, ServletRequest servletRequest) {
        StandardError standardError = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
    }

}
