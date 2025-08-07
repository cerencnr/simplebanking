package com.eteration.simplebanking.model;

import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@DiscriminatorValue("WITHDRAWAL")
public class WithdrawalTransaction extends Transaction {

    public WithdrawalTransaction(Double amount) {
        super(amount);
    }

    @Override
    public void apply(Account account) {
        account.setBalance(account.getBalance() - getAmount());
    }

}
