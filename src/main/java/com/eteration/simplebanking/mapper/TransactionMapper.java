package com.eteration.simplebanking.mapper;


import com.eteration.simplebanking.model.Transaction;
import com.eteration.simplebanking.model.response.TransactionResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionMapper {
    public TransactionResponse toResponse(Transaction transaction) {
        return new TransactionResponse(
                transaction.getTransactionId(),
                transaction.getDate(),
                transaction.getAmount()
        );
    }
}
