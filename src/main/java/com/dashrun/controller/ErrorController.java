package com.dashrun.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import com.dashrun.payload.WebResponse;

@RestControllerAdvice
public class ErrorController {
    
    // Metode ini akan menangani pengecualian ResponseStatusException
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<WebResponse<String>> responseStatusException(ResponseStatusException exception) {
        
        // Mengembalikan respons dengan status kode dari pengecualian
        return ResponseEntity
            .status(exception.getStatusCode()) // Mengatur status HTTP sesuai dengan pengecualian
            .body(WebResponse.<String>builder()
                .errors(exception.getReason()) // Menambahkan pesan kesalahan ke dalam body respons
                .build());
    }
}
