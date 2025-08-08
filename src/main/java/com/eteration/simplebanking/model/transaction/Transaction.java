package com.eteration.simplebanking.model.transaction;


import com.eteration.simplebanking.entity.BankAccount;
import com.eteration.simplebanking.entity.TransactionEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Positive;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
public abstract class Transaction {
    @JsonIgnore
    private final OffsetDateTime date;

    @Positive(message = "Amount of Transaction must be Positive!")
    private final double amount;

    @JsonIgnore
    private final String type;

    @JsonIgnore
    private final String approvalCode;

    public Transaction(double amount, String type) {
        this.date = OffsetDateTime.now();
        this.amount = amount;
        this.type = type;
        this.approvalCode = UUID.randomUUID().toString();
    }

    public TransactionEntity toEntity() {
        return new TransactionEntity(
                this.getDate(),
                this.getAmount(),
                this.getType(),
                this.getApprovalCode()
        );
    }

    public abstract void apply(BankAccount account);
}
