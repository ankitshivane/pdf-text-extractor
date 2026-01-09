package com.example.pdfextractor.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PdfProcessingException.class)
    public ResponseEntity<String> handle(PdfProcessingException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
