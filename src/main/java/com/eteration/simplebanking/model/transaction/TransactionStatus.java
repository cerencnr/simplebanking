package com.eteration.simplebanking.model.transaction;


import lombok.Data;

@Data
public class TransactionStatus {
    private final String status;
    private final String approvalCode;

    public static TransactionStatus ok(String approvalCode) {
        return new TransactionStatus("OK", approvalCode);
    }
}
