package com.eteration.simplebanking;


import com.eteration.simplebanking.entity.BankAccount;
import com.eteration.simplebanking.exception.InsufficientBalanceException;
import com.eteration.simplebanking.model.transaction.DepositTransaction;
import com.eteration.simplebanking.model.transaction.PhoneBillPaymentTransaction;
import com.eteration.simplebanking.model.transaction.WithdrawalTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ModelTest {
    @Test
    public void testCreateAccountAndSetBalance0() {
        BankAccount account = new BankAccount("Kerem Karaca", "17892");

        assertEquals("Kerem Karaca", account.getOwner());
        assertEquals("17892", account.getAccountNumber());
        assertEquals(0, account.getBalance());
    }

    @Test
    public void testDepositIntoBankAccount() {
        BankAccount account = new BankAccount("Demet Demircan", "9834");

        account.post(new DepositTransaction(100));
        assertEquals(100, account.getBalance());
    }

    @Test
    public void testWithdrawFromBankAccount() throws InsufficientBalanceException {
        BankAccount account = new BankAccount("Demet Demircan", "9834");

        account.post(new DepositTransaction(100));
        assertEquals(100, account.getBalance());

        account.post(new WithdrawalTransaction(50));
        assertEquals(50, account.getBalance());
    }

    @Test
    public void testWithdrawException() {
        Assertions.assertThrows(InsufficientBalanceException.class, () -> {
            BankAccount account = new BankAccount("Demet Demircan", "9834");
            account.post(new DepositTransaction(100));
            account.post(new WithdrawalTransaction(500));
        });
    }

    @Test
    public void testTransactions() throws InsufficientBalanceException {
        // Create account
        BankAccount account = new BankAccount("Canan Kaya", "1234");
        assertTrue(account.getTransactions().isEmpty());

        // Deposit Transaction
        DepositTransaction depositTrx = new DepositTransaction(100);
        assertNotNull(depositTrx.getDate());

        account.post(depositTrx);
        assertEquals(100, account.getBalance());
        assertEquals(1, account.getTransactions().size());

        // Withdrawal Transaction
        WithdrawalTransaction withdrawalTrx = new WithdrawalTransaction(60);
        assertNotNull(withdrawalTrx.getDate());

        account.post(withdrawalTrx);
        assertEquals(40, account.getBalance());
        assertEquals(2, account.getTransactions().size());
    }

    @Test
    public void testAllTransactions() throws InsufficientBalanceException {
        BankAccount account = new BankAccount("Jim", "12345");

        account.post(new DepositTransaction(1000));
        account.post(new WithdrawalTransaction(200));
        account.post(new PhoneBillPaymentTransaction("Vodafone", "5423345566", 96.50));

        assertEquals(703.50, account.getBalance(), 0.0001);
    }
}
