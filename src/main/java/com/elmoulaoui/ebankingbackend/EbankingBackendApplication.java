package com.elmoulaoui.ebankingbackend;

import com.elmoulaoui.ebankingbackend.dtos.BankAccountDTO;
import com.elmoulaoui.ebankingbackend.dtos.CurrentBankAccountDTO;
import com.elmoulaoui.ebankingbackend.dtos.CustomerDTO;
import com.elmoulaoui.ebankingbackend.dtos.SavingBankAccountDTO;
import com.elmoulaoui.ebankingbackend.entities.*;
import com.elmoulaoui.ebankingbackend.enums.AccountStatus;
import com.elmoulaoui.ebankingbackend.enums.OperationType;
import com.elmoulaoui.ebankingbackend.exceptions.BalanceNotSufficientException;
import com.elmoulaoui.ebankingbackend.exceptions.BankAccountNotFoundException;
import com.elmoulaoui.ebankingbackend.exceptions.CustomerNotFoundException;
import com.elmoulaoui.ebankingbackend.repositories.AccountOperationRepository;
import com.elmoulaoui.ebankingbackend.repositories.BankAccountRepository;
import com.elmoulaoui.ebankingbackend.repositories.CustomerRepository;
import com.elmoulaoui.ebankingbackend.services.BankAccountService;
import com.elmoulaoui.ebankingbackend.services.BankService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class EbankingBackendApplication {

    public static void main(String[] args) {

        SpringApplication.run(EbankingBackendApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(BankAccountService bankAccountService){
        return args -> {
            Stream.of("Adil","Amal","Maher").forEach(name->{
                CustomerDTO customer = new CustomerDTO();
                customer.setName(name);
                customer.setEmail(name+"@gmail.com");
                bankAccountService.saveCustomer(customer);
            });
            bankAccountService.listCustomer().forEach(customer -> {
                try {
                    bankAccountService.saveCurrentBankAccount(Math.random()*80000,8000, customer.getId());
                    bankAccountService.saveSavingBankAccount(Math.random()*110000,4.5, customer.getId());

                } catch (CustomerNotFoundException e) {
                    e.printStackTrace();
                }
            });
            List<BankAccountDTO> bankAccountList = bankAccountService.bankAccountList();
            for (BankAccountDTO bankAccount:bankAccountList){
                for (int i =0 ; i<10;i++){
                    String accountId;
                    if(bankAccount instanceof SavingBankAccountDTO){
                        accountId=((SavingBankAccountDTO) bankAccount).getId();
                    }else{
                        accountId=((CurrentBankAccountDTO) bankAccount).getId();
                    }
                    bankAccountService.credit(accountId, 10000+Math.random()*120000,"Credit");
                    bankAccountService.debit(accountId, 1000+Math.random()*8000,"Debit");
                }
            }
        };
    }
    //@Bean
    CommandLineRunner start(CustomerRepository customerRepository,
                            BankAccountRepository bankAccountRepository,
                            AccountOperationRepository accountOperationRepository) {
        return args -> {
            Stream.of("Amine","Ghita","Ibrahim").forEach(name->{
                Customer customer = new Customer();
                customer.setName(name);
                customer.setEmail(name+"@gmail.com");
                customerRepository.save(customer);
            });
            customerRepository.findAll().forEach(cust->{
                CurrentAccount currentAccount = new CurrentAccount();
                currentAccount.setId(UUID.randomUUID().toString());
                currentAccount.setBalance(Math.random()*60000);
                currentAccount.setCreatedAt(new Date());
                currentAccount.setStatus(AccountStatus.CREATED);
                currentAccount.setCustomer(cust);
                currentAccount.setOverDraft(6000);
                bankAccountRepository.save(currentAccount);

                SavingAccount savingAccount = new SavingAccount();
                savingAccount.setId(UUID.randomUUID().toString());
                savingAccount.setBalance(Math.random()*60000);
                savingAccount.setCreatedAt(new Date());
                savingAccount.setStatus(AccountStatus.CREATED);
                savingAccount.setCustomer(cust);
                savingAccount.setInterestRate(4.5);
                bankAccountRepository.save(savingAccount);
            });
            bankAccountRepository.findAll().forEach(acc-> {
                for (int i = 0; i < 10; i++) {
                    AccountOperation accountOperation = new AccountOperation();
                    accountOperation.setOperationDate(new Date());
                    accountOperation.setAmount(Math.random() * 11000);
                    accountOperation.setType(Math.random() > 0.5 ? OperationType.DEBIT : OperationType.CREDIT);
                    accountOperation.setBankAccount(acc);
                    accountOperationRepository.save(accountOperation);
                }


            });

        };
    }
}
