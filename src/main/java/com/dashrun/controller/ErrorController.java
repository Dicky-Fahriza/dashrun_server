package com.dashrun.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import com.dashrun.payload.WebResponse;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ErrorController {
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<WebResponse<String>> responseStatusException(ResponseStatusException exception) {

        // WebResponse<String> webResponse = new WebResponse<>();
        // response.setData(data: null);
        // response.setErrors(exception.getReason());

        return ResponseEntity.status(exception.getStatusCode())
        .body(WebResponse.<String>builder().errors(exception.getReason())
        .build());
    }
}
