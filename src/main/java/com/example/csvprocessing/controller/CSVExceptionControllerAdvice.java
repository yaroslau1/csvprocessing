package com.example.csvprocessing.controller;

import com.example.csvprocessing.message.Response;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Log4j2
@RestControllerAdvice
public class CSVExceptionControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleMaxSizeException(Exception exception) {
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new Response(exception.getMessage()));
    }
}
