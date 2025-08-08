package com.eteration.simplebanking.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorDTO {
    private final LocalDateTime timestamp;
    private final int status;
    private final String error;
}
