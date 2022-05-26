package com.elmoulaoui.ebankingbackend.entities;

import com.elmoulaoui.ebankingbackend.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)  //Pour l'heritage et le type de l'heritage pour les bases de données relationnelles sql
@DiscriminatorColumn(name = "TYPE",length = 4)
/*@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)*/
/*@Inheritance(strategy = InheritanceType.JOINED)*/
@Data @NoArgsConstructor @AllArgsConstructor

//public abstract class BankAccount {
public class BankAccount {   /*Quand on utilise TABLE_PER_CLASS on ait pas besoin d'une table BankAccount pour cela On note cette classe mère comme abstract */
    @Id
    private String id;
    private double balance;
    private Date createdAt;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    @ManyToOne
    private Customer customer;
    @OneToMany(mappedBy = "bankAccount", fetch = FetchType.LAZY) //mappedby utilisée quand on a une relation bidirectionnelle(OneToMany ManyToOne)
    //FetchType.LAZY (default value) signifie que quand vous chargez les infos du cmpt sa liste accountOperations ne va pas se charger, mais pour FetchType.EAGER elle va se charger
    private List<AccountOperation> accountOperations;
}
