package com.eteration.simplebanking;

import com.eteration.simplebanking.controller.AccountController;
import com.eteration.simplebanking.model.transaction.TransactionStatus;
import com.eteration.simplebanking.entity.BankAccount;
import com.eteration.simplebanking.exception.InsufficientBalanceException;
import com.eteration.simplebanking.model.transaction.DepositTransaction;
import com.eteration.simplebanking.model.transaction.WithdrawalTransaction;
import com.eteration.simplebanking.repository.AccountRepository;
import com.eteration.simplebanking.services.AccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
class ControllerTests {
    @Spy
    @InjectMocks
    private AccountController controller;

    @Spy
    private AccountService service = new AccountService(
            mock(AccountRepository.class)
    );

    @Test
    public void givenId_Credit_thenReturnJson() throws Exception {
        BankAccount account = new BankAccount("Kerem Karaca", "17892");
        doReturn(account).when(service).findAccount("17892");

        ResponseEntity<TransactionStatus> result = controller.credit("17892", new DepositTransaction(1000.0));
        verify(service, times(1)).findAccount("17892");

        assertNotNull(result.getBody());
        assertEquals("OK", result.getBody().getStatus());
    }

    @Test
    public void givenId_CreditAndThenDebit_thenReturnJson() throws Exception {
        BankAccount account = new BankAccount("Kerem Karaca", "17892");
        doReturn(account).when(service).findAccount("17892");

        ResponseEntity<TransactionStatus> result = controller.credit("17892", new DepositTransaction(1000.0));
        ResponseEntity<TransactionStatus> result2 = controller.debit("17892", new WithdrawalTransaction(50.0));
        verify(service, times(2)).findAccount("17892");

        assertNotNull(result.getBody());
        assertEquals("OK", result.getBody().getStatus());

        assertNotNull(result2.getBody());
        assertEquals("OK", result2.getBody().getStatus());

        assertEquals(950.0, account.getBalance(), 0.001);
    }

    @Test
    public void givenId_CreditAndThenDebitMoreGetException_thenReturnJson() throws Exception {
        Assertions.assertThrows(InsufficientBalanceException.class, () -> {
            BankAccount account = new BankAccount("Kerem Karaca", "17892");
            doReturn(account).when(service).findAccount("17892");

            ResponseEntity<TransactionStatus> result = controller.credit("17892", new DepositTransaction(1000.0));
            verify(service, times(1)).findAccount("17892");

            assertNotNull(result.getBody());
            assertEquals("OK", result.getBody().getStatus());
            assertEquals(1000.0, account.getBalance(), 0.001);

            controller.debit("17892", new WithdrawalTransaction(5000.0));
        });
    }

    @Test
    public void givenId_GetAccount_thenReturnJson() throws Exception {
        BankAccount account = new BankAccount("Kerem Karaca", "17892");
        doReturn(account).when(service).findAccount("17892");

        ResponseEntity<BankAccount> result = controller.getAccount("17892");
        verify(service, times(1)).findAccount("17892");

        assertEquals(account, result.getBody());
    }
}
