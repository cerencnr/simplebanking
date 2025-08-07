package com.eteration.simplebanking.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "transaction_type")
public abstract class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer transactionId;
    private LocalDateTime date;
    private Double amount;

    @ManyToOne
    private Account account;

    public Transaction(Double amount) {
        this.date = LocalDateTime.now();
        this.amount = amount;
    }

    public abstract void apply(Account account);

}
