package com.eteration.simplebanking.model.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionResponse {
    private final Integer transactionId;
    private final LocalDateTime date;
    private final Double amount;
}
