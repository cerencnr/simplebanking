package com.eteration.simplebanking.mapper;

import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.response.AccountResponse;
import com.eteration.simplebanking.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AccountMapper {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    public AccountResponse toResponse(Account account) {
        return new AccountResponse(
                account.getId(),
                account.getOwner(),
                account.getBalance(),
                transactionRepository.findAllByAccount(account)
                        .stream()
                        .map(transactionMapper::toResponse)
                        .collect(Collectors.toList())
        );
    }

}
