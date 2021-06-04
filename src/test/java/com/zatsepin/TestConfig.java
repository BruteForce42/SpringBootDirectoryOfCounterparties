package com.zatsepin;

import com.zatsepin.repository.CounterpartyRepository;
import com.zatsepin.service.CounterpartyService;
import com.zatsepin.service.CounterpartyServiceImpl;
import com.zatsepin.util.CounterpartyValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {

    @Autowired
    private CounterpartyRepository counterpartyRepository;

    @Bean
    public CounterpartyService counterpartyService() {
        return new CounterpartyServiceImpl(counterpartyRepository);
    }

    @Bean
    public CounterpartyValidator counterpartyValidator() {
        return new CounterpartyValidator(counterpartyService());
    }
}