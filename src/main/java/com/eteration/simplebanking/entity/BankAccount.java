package com.eteration.simplebanking.entity;

import com.eteration.simplebanking.exception.InsufficientBalanceException;
import com.eteration.simplebanking.model.transaction.Transaction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "account")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BankAccount {
    @Id
    private String accountNumber;
    private String owner;
    private double balance;
    private OffsetDateTime createDate = OffsetDateTime.now();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "account_transactions",
            joinColumns = @JoinColumn(name = "account_number"),
            inverseJoinColumns = @JoinColumn(name = "transaction_id")
    )
    private List<TransactionEntity> transactions = new ArrayList<>();

    public BankAccount(String owner, String accountNumber) {
        this.owner = owner;
        this.accountNumber = accountNumber;
        this.balance = 0.0;
        this.createDate = OffsetDateTime.now();
        this.transactions = new ArrayList<>();
    }

    public void setBalance(double balance) {
        if (balance < 0) {
            throw new InsufficientBalanceException("Insufficient Balance for Transaction");
        }
        this.balance = balance;
    }

    public void post(Transaction transaction) {
        transaction.apply(this);
        transactions.add(transaction.toEntity());
    }
}
