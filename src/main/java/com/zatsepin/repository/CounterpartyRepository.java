package com.zatsepin.repository;

import com.zatsepin.entity.Counterparty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CounterpartyRepository extends JpaRepository<Counterparty, Long> {
    Counterparty findByName(String name);
    List<Counterparty> findByAccountNumberAndBic(String accountNumber, String bic);
}
