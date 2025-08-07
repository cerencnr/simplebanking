package com.eteration.simplebanking.model;

import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@DiscriminatorValue("DEPOSIT")
public class DepositTransaction extends Transaction {

    public DepositTransaction(Double amount) {
        super(amount);
    }

    @Override
    public void apply(Account account) {
        account.setBalance(account.getBalance() + getAmount());
    }

}
