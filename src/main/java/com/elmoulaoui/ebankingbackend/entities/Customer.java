package com.elmoulaoui.ebankingbackend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity //pour le mapping objet relationnel

@Data @NoArgsConstructor @AllArgsConstructor
public class Customer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String name;
    private  String email;
    @OneToMany(mappedBy = "customer")  //mappedby utilisée quand on a une relation bidirectionnelle(OneToMany ManyToOne)
    private List<BankAccount> bankAccounts;
}
