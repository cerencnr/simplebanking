package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.entity.BankAccount;
import com.eteration.simplebanking.model.transaction.DepositTransaction;
import com.eteration.simplebanking.model.transaction.PhoneBillPaymentTransaction;
import com.eteration.simplebanking.model.transaction.TransactionStatus;
import com.eteration.simplebanking.model.transaction.WithdrawalTransaction;
import com.eteration.simplebanking.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account/v1")
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/{accountNumber}")
    public ResponseEntity<BankAccount> getAccount(@PathVariable String accountNumber) {
        return ResponseEntity.ok(accountService.findAccount(accountNumber));
    }

    @PostMapping("/credit/{accountNumber}")
    public ResponseEntity<TransactionStatus> credit(@PathVariable String accountNumber, @Valid @RequestBody DepositTransaction depositTransaction) {
        return ResponseEntity.ok(accountService.postTransaction(accountNumber, depositTransaction));
    }

    @PostMapping("/debit/{accountNumber}")
    public ResponseEntity<TransactionStatus> debit(@PathVariable String accountNumber, @Valid @RequestBody WithdrawalTransaction withdrawalTransaction) {
        return ResponseEntity.ok(accountService.postTransaction(accountNumber, withdrawalTransaction));
    }

    @PostMapping("/phone-bill/{accountNumber}")
    public ResponseEntity<TransactionStatus> phoneBill(@PathVariable String accountNumber, @Valid @RequestBody PhoneBillPaymentTransaction phoneBillPaymentTransaction) {
        return ResponseEntity.ok(accountService.postTransaction(accountNumber, phoneBillPaymentTransaction));
    }
}
