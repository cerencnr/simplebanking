package com.eteration.simplebanking.model.transaction;


import com.eteration.simplebanking.entity.BankAccount;
import com.fasterxml.jackson.annotation.JsonCreator;

public class DepositTransaction extends Transaction {
    @JsonCreator
    public DepositTransaction(double amount) {
        super(amount, "DepositTransaction");
    }

    @Override
    public void apply(BankAccount account) {
        account.setBalance(account.getBalance() + getAmount());
    }
}
