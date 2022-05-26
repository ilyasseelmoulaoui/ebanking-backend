package com.elmoulaoui.ebankingbackend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity

@DiscriminatorValue("CA")   //si on utilise TABLE_PER_CLASS cette annotation n'a plus de sens
@Data @NoArgsConstructor @AllArgsConstructor
public class CurrentAccount  extends  BankAccount{
    private double overDraft;
}
