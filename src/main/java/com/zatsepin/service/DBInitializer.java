package com.zatsepin.service;

import com.zatsepin.entity.Counterparty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Инициализирует БД при запуске приложения.
 */
@Service
public class DBInitializer {

    private static Logger logger = LoggerFactory.getLogger(DBInitializer.class);

    CounterpartyService counterpartyService;

    @Autowired
    public DBInitializer(CounterpartyService counterpartyService) {
        this.counterpartyService = counterpartyService;
    }

    @PostConstruct
    public void initDB() {
        logger.info("Starting database initialization...");

        Counterparty counterparty = Counterparty.builder()
                .name("Факториал")
                .inn("5504081248")
                .kpp("550401001")
                .accountNumber("40702810310430001226")
                .bic("044525411")
                .comment("Обслуживание домофонов")
                .build();
        counterpartyService.save(counterparty);

        counterparty = Counterparty.builder()
                .name("Энергосбыт")
                .inn("5503248039")
                .kpp("550401001")
                .accountNumber("40702810445000093711")
                .bic("045209673")
                .comment("Омская энергосбытовая комапания")
                .build();
        counterpartyService.save(counterparty);

        counterparty = Counterparty.builder()
                .name("Магнит")
                .inn("5401381810")
                .kpp("550401001")
                .accountNumber("40702810338320002761")
                .bic("046577964")
                .comment("Вывоз бытового мусора")
                .build();
        counterpartyService.save(counterparty);

        counterparty = Counterparty.builder()
                .name("Водоканал")
                .inn("5504097128")
                .kpp("550401001")
                .accountNumber("40702810045370100747")
                .bic("045209673")
                .comment("Водоснабжение и канализация")
                .build();
        counterpartyService.save(counterparty);

        counterparty = Counterparty.builder()
                .name("Капремонт")
                .inn("5503239348")
                .kpp("550301001")
                .accountNumber("40603810701800000001")
                .bic("045004867")
                .comment("Фонд капитального ремонта жилых домов")
                .build();
        counterpartyService.save(counterparty);

        counterparty = Counterparty.builder()
                .name("Тепловая")
                .inn("5501016762")
                .kpp("550301001")
                .accountNumber("40821810745000000013")
                .bic("045209673")
                .comment("Поставка ГВС")
                .build();
        counterpartyService.save(counterparty);

        counterparty = Counterparty.builder()
                .name("Газпром")
                .inn("5501174543")
                .kpp("550101001")
                .accountNumber("40702810445380130909")
                .bic("045209673")
                .comment("Газоснабжение")
                .build();
        counterpartyService.save(counterparty);

        counterparty = Counterparty.builder()
                .name("Отопление")
                .inn("5503249258")
                .kpp("550301001")
                .accountNumber("40702810500000022738")
                .bic("044525823")
                .comment("Распределительные тепловые сети")
                .build();
        counterpartyService.save(counterparty);

        counterparty = Counterparty.builder()
                .name("ЖКХ")
                .inn("5507201659")
                .kpp("550701001")
                .accountNumber("40702810505010000237")
                .bic("045209777")
                .comment("Обслуживание ЖКХ")
                .build();
        counterpartyService.save(counterparty);

        logger.info("Database initialization finished.");
    }
}
