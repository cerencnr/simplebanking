package com.eteration.simplebanking.model.response;

import lombok.Data;

import java.util.List;

@Data
public class AccountResponse {
    private final String id;
    private final String owner;
    private final Double balance;
    private final List<TransactionResponse> transactions;
}
