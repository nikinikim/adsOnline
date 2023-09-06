package com.example.adsonline.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(BusinessLogicException.class)
    public ResponseEntity<String> handleBusinessException(BusinessLogicException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(ex.getHttpStatus()).body(ex.getMessage());
    }
}
