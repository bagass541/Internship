package com.bagas.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionApiHandler {

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<String> categoryNotFoundException(CategoryNotFoundException e) {
        return createNotFoundResponseEntity(e);
    }

    @ExceptionHandler(SubcategoryNotFoundException.class)
    public ResponseEntity<String> subcategoryNotFoundException(SubcategoryNotFoundException e) {
        return createNotFoundResponseEntity(e);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> productNotFoundException(ProductNotFoundException e) {
        return createNotFoundResponseEntity(e);
    }

    @ExceptionHandler(GroupNotFoundException.class)
    public ResponseEntity<String> groupNotFoundException(GroupNotFoundException e) {
        return createNotFoundResponseEntity(e);
    }

    private ResponseEntity<String> createNotFoundResponseEntity(Exception e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }
}
