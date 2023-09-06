package com.example.adsonline.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BusinessLogicException extends RuntimeException {
    private final HttpStatus httpStatus;

    public BusinessLogicException(String message) {
        super(message);
        this.httpStatus = HttpStatus.CONFLICT;
    }

    public BusinessLogicException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
