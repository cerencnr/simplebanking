package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.exception.BusinessException;
import com.eteration.simplebanking.exception.ErrorDTO;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalControllerAdvice.class);

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorDTO> customHandleBusinessException(BusinessException ex, WebRequest request) {
        LOGGER.info("Business Error: {}", ex.getMessage());
        HttpStatus httpStatus = ex.getStatus();
        return new ResponseEntity<>(
                new ErrorDTO(
                        LocalDateTime.now(),
                        httpStatus.value(),
                        ex.getMessage()
                ), httpStatus
        );
    }

    @Override
    @NonNull
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatus status,
            @NonNull WebRequest request
    ) {
        LOGGER.info("Validation Error: {}", ex.getMessage());
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(
                new ErrorDTO(
                        LocalDateTime.now(),
                        status.value(),
                        "Validation failed: " + ex.getBindingResult().getFieldError().getDefaultMessage()
                ), httpStatus
        );
    }
}
