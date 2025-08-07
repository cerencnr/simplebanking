package com.eteration.simplebanking.services;


import com.eteration.simplebanking.controller.TransactionStatus;
import com.eteration.simplebanking.mapper.AccountMapper;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.DepositTransaction;
import com.eteration.simplebanking.model.Transaction;
import com.eteration.simplebanking.model.WithdrawalTransaction;
import com.eteration.simplebanking.model.request.DepositTransactionRequest;
import com.eteration.simplebanking.model.request.WithdrawalTransactionRequest;
import com.eteration.simplebanking.model.response.AccountResponse;
import com.eteration.simplebanking.repository.AccountRepository;
import com.eteration.simplebanking.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final AccountMapper accountMapper;

    public AccountResponse findAccount(String accountNumber) {
        return accountMapper.toResponse(accountRepository
                .findById(accountNumber)
                .orElseThrow(
                        () -> new RuntimeException("Not Found")
                )
        );
    }

    public ResponseEntity<TransactionStatus> credit(String accountNumber, DepositTransactionRequest transactionRequest) {
        Account account = accountRepository.findById(accountNumber).orElseThrow(
                () -> new RuntimeException("Not Found")
        );
        Transaction depositTransaction = new DepositTransaction(transactionRequest.getAmount());
        account.post(depositTransaction);
        accountRepository.save(account);
        depositTransaction.setAccount(account);
        transactionRepository.save(depositTransaction);

        TransactionStatus transactionStatus = new TransactionStatus("OK");
        ResponseEntity<TransactionStatus> responseEntity = new ResponseEntity<>(transactionStatus, HttpStatus.OK);
        return responseEntity;
    }

    public ResponseEntity<TransactionStatus> debit(String accountNumber, WithdrawalTransactionRequest transactionRequest) {
        Account account = accountRepository.findById(accountNumber).orElseThrow(
                () -> new RuntimeException("Not Found")
        );
        Transaction withdrawalTransaction = new WithdrawalTransaction(transactionRequest.getAmount());
        account.post(withdrawalTransaction);
        accountRepository.save(account);
        withdrawalTransaction.setAccount(account);
        transactionRepository.save(withdrawalTransaction);

        TransactionStatus transactionStatus = new TransactionStatus("OK");
        ResponseEntity<TransactionStatus> responseEntity = new ResponseEntity<>(transactionStatus, HttpStatus.OK);
        return responseEntity;
    }
}
