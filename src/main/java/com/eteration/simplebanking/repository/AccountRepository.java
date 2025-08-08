package com.eteration.simplebanking.repository;

import com.eteration.simplebanking.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<BankAccount, String> {
}
