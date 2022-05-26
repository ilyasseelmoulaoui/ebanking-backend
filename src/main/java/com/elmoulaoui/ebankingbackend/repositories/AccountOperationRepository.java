package com.elmoulaoui.ebankingbackend.repositories;

import com.elmoulaoui.ebankingbackend.entities.AccountOperation;
import com.elmoulaoui.ebankingbackend.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountOperationRepository extends JpaRepository<AccountOperation,Long> {
}
