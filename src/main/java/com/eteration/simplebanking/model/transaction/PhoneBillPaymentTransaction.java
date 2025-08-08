package com.eteration.simplebanking.model.transaction;

import com.eteration.simplebanking.entity.BankAccount;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public class PhoneBillPaymentTransaction extends Transaction {
    private final String operator;
    private final String phoneNumber;

    @JsonCreator
    public PhoneBillPaymentTransaction(
            String operator,
            String phoneNumber,
            double amount
    ) {
        super(amount, "PhoneBillPaymentTransaction");
        this.operator = operator;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void apply(BankAccount account) {
        account.setBalance(account.getBalance() - getAmount());
    }
}
