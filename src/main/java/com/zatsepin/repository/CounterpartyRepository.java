package com.zatsepin.repository;

import com.zatsepin.entity.Counterparty;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для сохранения и извлечения хранимой сущности контрагента.
 */
public interface CounterpartyRepository extends JpaRepository<Counterparty, Long> {
    Counterparty findByName(String name);

    Counterparty findByAccountNumberAndBic(String accountNumber, String bic);
}
