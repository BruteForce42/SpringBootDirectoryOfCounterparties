package com.zatsepin.service;

import com.zatsepin.TestConfig;
import com.zatsepin.entity.Counterparty;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Import(TestConfig.class)
class CounterpartyServiceImplTest {

    @Autowired
    CounterpartyService counterpartyService;

    Counterparty counterparty;

    @BeforeEach
    void setUp() {
        counterparty = Counterparty.builder()
                .name("Водоканал")
                .inn("5504097128")
                .kpp("550401001")
                .accountNumber("40702810045370100747")
                .bic("045209673")
                .comment("Водоснабжение и канализация")
                .build();
    }

    @AfterEach
    void tearDown() {
        counterparty = null;
    }

    @Test
    void saveAndDeleteAndFindAll() {
        counterparty = counterpartyService.save(counterparty);
        assertEquals(1, counterpartyService.findAll().size());
        counterpartyService.delete(counterparty.getId());
        assertEquals(0, counterpartyService.findAll().size());
    }

    @Test
    void findById() {
        counterparty = counterpartyService.save(counterparty);
        Counterparty counterparty2 = counterpartyService.findById(counterparty.getId()).get();
        assertEquals(counterparty.getName(), counterparty2.getName());
    }

    @Test
    void findByName() {
        counterparty = counterpartyService.save(counterparty);
        Counterparty counterparty2 = counterpartyService.findByName(counterparty.getName());
        assertEquals(counterparty.getId(), counterparty2.getId());
    }

    @Test
    void findByAccountNumberAndBic() {
        counterparty = counterpartyService.save(counterparty);
        Counterparty counterparty2 = counterpartyService.findByAccountNumberAndBic(counterparty.getAccountNumber(), counterparty.getBic());
        assertEquals(counterparty.getId(), counterparty2.getId());
    }
}