package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.model.request.DepositTransactionRequest;
import com.eteration.simplebanking.model.request.WithdrawalTransactionRequest;
import com.eteration.simplebanking.model.response.AccountResponse;
import com.eteration.simplebanking.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/{accountNumber}")
    public AccountResponse getAccount(@PathVariable String accountNumber) {
        return accountService.findAccount(accountNumber);
    }

    @PostMapping("/{accountNumber}/credit")
    public ResponseEntity<TransactionStatus> credit(@PathVariable String accountNumber, @RequestBody DepositTransactionRequest transaction) {
        return accountService.credit(accountNumber, transaction);
    }

    @PostMapping("/{accountNumber}/debit")
    public ResponseEntity<TransactionStatus> debit(@PathVariable String accountNumber, @RequestBody WithdrawalTransactionRequest transaction) {
        return accountService.debit(accountNumber, transaction);
    }
}
