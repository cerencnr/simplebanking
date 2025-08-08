package com.eteration.simplebanking.services;


import com.eteration.simplebanking.model.transaction.TransactionStatus;
import com.eteration.simplebanking.entity.BankAccount;
import com.eteration.simplebanking.exception.BusinessException;
import com.eteration.simplebanking.model.transaction.Transaction;
import com.eteration.simplebanking.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public BankAccount findAccount(String accountNumber) {
        return accountRepository
                .findById(accountNumber)
                .orElseThrow(
                        () -> new BusinessException("BankAccount not found", HttpStatus.NOT_FOUND)
                );
    }

    public TransactionStatus postTransaction(String accountNumber, Transaction transaction) {
        BankAccount account = findAccount(accountNumber);
        account.post(transaction);

        accountRepository.save(account);
        return TransactionStatus.ok(transaction.getApprovalCode());
    }
}
