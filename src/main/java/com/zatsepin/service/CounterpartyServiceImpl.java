package com.zatsepin.service;

import com.zatsepin.entity.Counterparty;
import com.zatsepin.repository.CounterpartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("counterpartyService")
public class CounterpartyServiceImpl implements CounterpartyService {

    private final CounterpartyRepository counterpartyRepository;

    @Autowired
    public CounterpartyServiceImpl(CounterpartyRepository counterpartyRepository) {
        this.counterpartyRepository = counterpartyRepository;
    }

    @Override
    public List<Counterparty> findAll() {
        return new ArrayList<>(counterpartyRepository.findAll());
    }

    @Override
    public Counterparty save(Counterparty counterparty) {
        return counterpartyRepository.save(counterparty);
    }

    @Override
    public void delete(Long id) {
        counterpartyRepository.deleteById(id);
    }

    @Override
    public Optional<Counterparty> findById(Long id) {
        return counterpartyRepository.findById(id);
    }

    @Override
    public Counterparty findByName(String name) {
        return counterpartyRepository.findByName(name);
    }

    @Override
    public Counterparty findByAccountNumberAndBic(String accountNumber, String bic) {
        return counterpartyRepository.findByAccountNumberAndBic(accountNumber, bic);
    }
}
