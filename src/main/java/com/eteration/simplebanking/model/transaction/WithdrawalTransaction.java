package com.eteration.simplebanking.model.transaction;


import com.eteration.simplebanking.entity.BankAccount;
import com.fasterxml.jackson.annotation.JsonCreator;

public class WithdrawalTransaction extends Transaction {
    @JsonCreator
    public WithdrawalTransaction(double amount) {
        super(amount, "WithdrawalTransaction");
    }

    @Override
    public void apply(BankAccount account) {
        account.setBalance(account.getBalance() - getAmount());
    }
}
