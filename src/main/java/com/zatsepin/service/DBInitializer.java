package com.zatsepin.service;

import com.zatsepin.entity.Counterparty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

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

        Counterparty counterparty = new Counterparty();
        counterparty.setName("Факториал");
        counterparty.setInn("5504081248");
        counterparty.setKpp("550401001");
        counterparty.setAccountNumber("40702810310430001226");
        counterparty.setBic("044525411");
        counterparty.setComment("Обслуживание домофонов");
        counterpartyService.save(counterparty);

        counterparty = new Counterparty();
        counterparty.setName("Энергосбыт");
        counterparty.setInn("5503248039");
        counterparty.setKpp("550401001");
        counterparty.setAccountNumber("40702810445000093711");
        counterparty.setBic("045209673");
        counterparty.setComment("Омская энергосбытовая комапания");
        counterpartyService.save(counterparty);

        counterparty = new Counterparty();
        counterparty.setName("Магнит");
        counterparty.setInn("5401381810");
        counterparty.setKpp("550401001");
        counterparty.setAccountNumber("40702810338320002761");
        counterparty.setBic("046577964");
        counterparty.setComment("Вывоз бытового мусора");
        counterpartyService.save(counterparty);

        counterparty = new Counterparty();
        counterparty.setName("Водоканал");
        counterparty.setInn("5504097128");
        counterparty.setKpp("550401001");
        counterparty.setAccountNumber("40702810045370100747");
        counterparty.setBic("045209673");
        counterparty.setComment("Водоснабжение и канализация");
        counterpartyService.save(counterparty);

        counterparty = new Counterparty();
        counterparty.setName("Капремонт");
        counterparty.setInn("5503239348");
        counterparty.setKpp("550301001");
        counterparty.setAccountNumber("40603810701800000001");
        counterparty.setBic("045004867");
        counterparty.setComment("Фонд капитального ремонта жилых домов");
        counterpartyService.save(counterparty);

        counterparty = new Counterparty();
        counterparty.setName("Тепловая");
        counterparty.setInn("5501016762");
        counterparty.setKpp("550301001");
        counterparty.setAccountNumber("40821810745000000013");
        counterparty.setBic("045209673");
        counterparty.setComment("Поставка ГВС");
        counterpartyService.save(counterparty);

        counterparty = new Counterparty();
        counterparty.setName("Газпром");
        counterparty.setInn("5501174543");
        counterparty.setKpp("550101001");
        counterparty.setAccountNumber("40702810445380130909");
        counterparty.setBic("045209673");
        counterparty.setComment("Газоснабжение");
        counterpartyService.save(counterparty);

        counterparty = new Counterparty();
        counterparty.setName("Отопление");
        counterparty.setInn("5503249258");
        counterparty.setKpp("550301001");
        counterparty.setAccountNumber("40702810500000022738");
        counterparty.setBic("044525823");
        counterparty.setComment("Распределительные тепловые сети");
        counterpartyService.save(counterparty);

        counterparty = new Counterparty();
        counterparty.setName("ЖКХ");
        counterparty.setInn("5507201659");
        counterparty.setKpp("550701001");
        counterparty.setAccountNumber("40702810505010000237");
        counterparty.setBic("045209777");
        counterparty.setComment("Обслуживание ЖКХ");
        counterpartyService.save(counterparty);

        logger.info("Database initialization finished.");
    }
}
