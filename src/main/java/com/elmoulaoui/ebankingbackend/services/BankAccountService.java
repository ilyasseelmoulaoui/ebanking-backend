package com.elmoulaoui.ebankingbackend.services;

import com.elmoulaoui.ebankingbackend.entities.BankAccount;
import com.elmoulaoui.ebankingbackend.entities.CurrentAccount;
import com.elmoulaoui.ebankingbackend.entities.Customer;
import com.elmoulaoui.ebankingbackend.entities.SavingAccount;
import com.elmoulaoui.ebankingbackend.exceptions.BalanceNotSufficientException;
import com.elmoulaoui.ebankingbackend.exceptions.BankAccountNotFoundException;
import com.elmoulaoui.ebankingbackend.exceptions.CustomerNotFoundException;

import java.util.List;

public interface BankAccountService {
    Customer saveCustomer(Customer customer);
    CurrentAccount saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException;
    SavingAccount saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException;
    List<Customer> listCustomer();
    BankAccount getBankAccount(String accountId) throws BankAccountNotFoundException;

    void debit(String accountId,double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException;
    void credit(String accountId,double amount, String description) throws BankAccountNotFoundException;
    void transfer(String accountIdSrc, String accountIdDst, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException;

    List<BankAccount> bankAccountList();
}
