package com.eteration.simplebanking.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = true)
public class BusinessException extends RuntimeException {
    private final String message;
    private final HttpStatus status;

    public BusinessException(String message, HttpStatus status) {
        super(message);
        this.message = message;
        this.status = status;
    }
}
