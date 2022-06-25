package com.elmoulaoui.ebankingbackend.services;

import com.elmoulaoui.ebankingbackend.dtos.*;
import com.elmoulaoui.ebankingbackend.entities.BankAccount;
import com.elmoulaoui.ebankingbackend.entities.CurrentAccount;
import com.elmoulaoui.ebankingbackend.entities.Customer;
import com.elmoulaoui.ebankingbackend.entities.SavingAccount;
import com.elmoulaoui.ebankingbackend.exceptions.BalanceNotSufficientException;
import com.elmoulaoui.ebankingbackend.exceptions.BankAccountNotFoundException;
import com.elmoulaoui.ebankingbackend.exceptions.CustomerNotFoundException;

import java.util.List;

public interface BankAccountService {
    CustomerDTO saveCustomer(CustomerDTO customerDTO);
    CurrentBankAccountDTO saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException;
    SavingBankAccountDTO saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException;
    List<CustomerDTO> listCustomer();
    BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException;

    void debit(String accountId,double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException;
    void credit(String accountId,double amount, String description) throws BankAccountNotFoundException;
    void transfer(String accountIdSrc, String accountIdDst, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException;

    List<BankAccountDTO> bankAccountList();

    CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException;

    CustomerDTO updateCustomer(CustomerDTO customerDTO);

    void deleteCustomer(Long customerId);

    List<AccountOperationDTO> accountHistory(String accountId);

    AccountHistoryDTO getAccountHistory(String accountId, int page, int size) throws BankAccountNotFoundException;

    List<CustomerDTO> searchCustomers(String keyword);
}
