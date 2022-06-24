package com.elmoulaoui.ebankingbackend.dtos;

import com.elmoulaoui.ebankingbackend.entities.BankAccount;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Data @NoArgsConstructor @AllArgsConstructor
public class CustomerDTO {

    private Long id;
    private  String name;
    private  String email;

}
