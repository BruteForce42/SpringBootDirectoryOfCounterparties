package com.zatsepin.service;

import com.zatsepin.entity.Counterparty;

import java.util.List;
import java.util.Optional;

public interface CounterpartyService {
    List<Counterparty> findAll();
    Counterparty save(Counterparty counterparty);
    void delete(Long id);
    Optional<Counterparty> findById(Long id);
    Counterparty findByName(String name);
    Counterparty findByAccountNumberAndBic(String accountNumber, String bic);
}
