package com.elmoulaoui.ebankingbackend.services;

import com.elmoulaoui.ebankingbackend.entities.BankAccount;
import com.elmoulaoui.ebankingbackend.entities.Customer;
import com.elmoulaoui.ebankingbackend.exceptions.CustomerNotFoundException;

import java.util.List;

public interface BankAccountService {
    Customer saveCustomer(Customer customer);
    BankAccount saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException;
    BankAccount saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException;
    List<Customer> listCustomer();
    BankAccount getBankAccount(String accountId);

    void debit(String accountId,double amount, String description);
    void credit(String accountId,double amount, String description);
    void transfer(String accountIdSrc, String accountIdDst, double amount);

}
