package com.elmoulaoui.ebankingbackend.repositories;

import com.elmoulaoui.ebankingbackend.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
