package com.eteration.simplebanking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account {

    @Id
    private String id;
    private String owner;
    private Double balance;

    public Account(String owner, String accountNumber) {
        this(owner, accountNumber, 0.0);
    }

    public void post(Transaction transaction) {
        transaction.apply(this);
    }

}
