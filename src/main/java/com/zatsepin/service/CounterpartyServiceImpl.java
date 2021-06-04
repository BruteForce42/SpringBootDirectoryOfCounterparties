package com.zatsepin.service;

import com.zatsepin.entity.Counterparty;
import com.zatsepin.repository.CounterpartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Предоставляет реализации методов для сохранения, извлечения и поиска хранимых сущностей контрагентов.
 */
@Service("counterpartyService")
public class CounterpartyServiceImpl implements CounterpartyService {

    private final CounterpartyRepository counterpartyRepository;

    @Autowired
    public CounterpartyServiceImpl(CounterpartyRepository counterpartyRepository) {
        this.counterpartyRepository = counterpartyRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Counterparty> findAll() {
        return new ArrayList<>(counterpartyRepository.findAll());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Counterparty save(Counterparty counterparty) {
        return counterpartyRepository.save(counterparty);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) {
        counterpartyRepository.deleteById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Counterparty> findById(Long id) {
        return counterpartyRepository.findById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Counterparty findByName(String name) {
        return counterpartyRepository.findByName(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Counterparty findByAccountNumberAndBic(String accountNumber, String bic) {
        return counterpartyRepository.findByAccountNumberAndBic(accountNumber, bic);
    }
}
