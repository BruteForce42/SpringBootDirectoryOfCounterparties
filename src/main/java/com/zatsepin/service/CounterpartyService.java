package com.zatsepin.service;

import com.zatsepin.entity.Counterparty;

import java.util.List;
import java.util.Optional;

/**
 * Предоставляет контракты методов для сохранения, извлечения и поиска хранимых сущностей контрагентов.
 */
public interface CounterpartyService {

    /**
     * Извлекает из БД  и возвращает список всех контрагентов.
     */
    List<Counterparty> findAll();

    /**
     * Сохраняет переданного контагента в БД.
     */
    Counterparty save(Counterparty counterparty);

    /**
     * Удаляет из БД контрагента по переданному идентификатору.
     */
    void delete(Long id);

    /**
     * Находит и возвращает из БД контрагента по переданному идентификатору.
     */
    Optional<Counterparty> findById(Long id);

    /**
     * Находит и возвращает из БД контрагента по переданному наименованию.
     */
    Counterparty findByName(String name);

    /**
     * Находит и возвращает из БД контрагента по переданным номеру счёта и БИК.
     */
    Counterparty findByAccountNumberAndBic(String accountNumber, String bic);
}
